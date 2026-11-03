package willydekeyser.youtube_statistics.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import willydekeyser.youtube_statistics.model.*;

import java.time.Duration;

@Service
public class YouTubeService {

    @Value("${youtube.api.key}")
    private String apiKey;

    @Value("${youtube.api.url}")
    private String apiUrl;

    private final RestClient restClient = RestClient.create();

    public VideoDetails getVideoDetails(String videoId) {
        String url = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("part", "snippet,statistics,contentDetails")
                .queryParam("id", videoId)
                .queryParam("key", apiKey)
                .toUriString();

        YouTubeResponse response = restClient.get()
                .uri(url)
                .retrieve()
                .body(YouTubeResponse.class);

        if (response != null && response.items() != null && !response.items().isEmpty()) {
            VideoItem item = response.items().getFirst();
            Snippet snippet = item.snippet();
            Statistics stats = item.statistics();
            ContentDetails details = item.contentDetails();

            return new VideoDetails(
                    snippet != null ? snippet.title() : "No title",
                    snippet != null ? snippet.description() : "",
                    details != null ? convert(details.duration()) : "Unknown",
                    snippet != null ? snippet.channelTitle() : "",
                    snippet != null ? snippet.publishedAt() : "",
                    (stats != null && stats.viewCount() != null) ? Long.parseLong(stats.viewCount()) : 0,
                    (stats != null && stats.likeCount() != null) ? Long.parseLong(stats.likeCount()) : 0,
                    (stats != null && stats.commentCount() != null) ? Long.parseLong(stats.commentCount()) : 0
            );
        }

        throw new RuntimeException("Video not found or no statistics available.");
    }

    public ChannelStatistics getChannelDetails(String channelId) {
        String url = UriComponentsBuilder.fromUriString("https://www.googleapis.com/youtube/v3/channels")
                .queryParam("part", "snippet,statistics,contentDetails,brandingSettings")
                .queryParam("id", channelId)
                .queryParam("key", apiKey)
                .toUriString();

        YouTubeChannelApiResponse response = restClient.get()
                .uri(url)
                .retrieve()
                .body(YouTubeChannelApiResponse.class);

        if (response != null && !response.items().isEmpty()) {
            System.out.println(response.items().getFirst());
            return response.items().getFirst().statistics();
        }

        throw new RuntimeException("Channel not found for ID: " + channelId);
    }

    private String convert(String duration) {
        Duration time = Duration.parse(duration);
        return time.toMinutes() + ":" + time.toSecondsPart();
    }
}