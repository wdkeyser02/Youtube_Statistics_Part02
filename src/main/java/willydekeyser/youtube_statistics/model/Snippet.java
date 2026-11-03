package willydekeyser.youtube_statistics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Snippet(
        String title,
        String description,
        String publishedAt,
        String channelTitle) {}
