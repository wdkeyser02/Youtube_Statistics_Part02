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

        if (response != null && response.getItems() != null && !response.getItems().isEmpty()) {
            VideoItem item = response.getItems().getFirst();
            Snippet snippet = item.getSnippet();
            Statistics stats = item.getStatistics();
            ContentDetails details = item.getContentDetails();

            return new VideoDetails(
                    snippet != null ? snippet.getTitle() : "No title",
                    snippet != null ? snippet.getDescription() : "",
                    details != null ? convert(details.getDuration()) : "Unknown",
                    snippet != null ? snippet.getChannelTitle() : "",
                    snippet != null ? snippet.getPublishedAt() : "",
                    (stats != null && stats.getViewCount() != null) ? Long.parseLong(stats.getViewCount()) : 0,
                    (stats != null && stats.getLikeCount() != null) ? Long.parseLong(stats.getLikeCount()) : 0,
                    (stats != null && stats.getCommentCount() != null) ? Long.parseLong(stats.getCommentCount()) : 0
            );
        }

        throw new RuntimeException("Video not found or no statistics available.");
    }

    private String convert(String duration) {
        Duration time = Duration.parse(duration);
        return time.toMinutes() + ":" + time.toSecondsPart();
    }
}