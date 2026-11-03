package willydekeyser.youtube_statistics.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import willydekeyser.youtube_statistics.model.*;

@Service
public class YouTubeService {

    @Value("${youtube.api.key}")
    private String apiKey;

    @Value("${youtube.api.url}")
    private String apiUrl;

    private final RestClient restClient = RestClient.create();

    public YouTubeVideoApiResponse.VideoItem getVideoDetails(String videoId) {
        String url = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("part", "snippet,statistics,contentDetails")
                .queryParam("id", videoId)
                .queryParam("key", apiKey)
                .toUriString();

        YouTubeVideoApiResponse response = restClient.get()
                .uri(url)
                .retrieve()
                .body(YouTubeVideoApiResponse.class);

        if (response != null && response.items() != null && !response.items().isEmpty()) {
            return response.items().getFirst();
        }

        throw new RuntimeException("Video not found or no statistics available.");
    }

    public YouTubeChannelApiResponse.ChannelItem getChannelDetails(String channelId) {
        String url = UriComponentsBuilder.fromUriString("https://www.googleapis.com/youtube/v3/channels")
                .queryParam("part", "snippet,statistics,contentDetails,brandingSettings")
                .queryParam("id", channelId)
                .queryParam("key", apiKey)
                .toUriString();

        YouTubeChannelApiResponse response = restClient.get()
                .uri(url)
                .retrieve()
                .body(YouTubeChannelApiResponse.class);

        if(response != null && !response.items().isEmpty()) {
            return response.items().getFirst();
        }

        throw new RuntimeException("Channel not found for ID: " + channelId);
    }

}