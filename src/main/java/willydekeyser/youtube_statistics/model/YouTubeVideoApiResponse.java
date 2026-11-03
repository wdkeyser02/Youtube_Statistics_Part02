package willydekeyser.youtube_statistics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record YouTubeVideoApiResponse(
    List<VideoItem> items ) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record VideoItem (
            Snippet snippet,
            Statistics statistics,
            ContentDetails contentDetails) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record Snippet(
            String title,
            String description,
            String publishedAt,
            String channelTitle) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record Statistics (
            String viewCount,
            String likeCount,
            String commentCount) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record ContentDetails (
            String duration) {}

}

