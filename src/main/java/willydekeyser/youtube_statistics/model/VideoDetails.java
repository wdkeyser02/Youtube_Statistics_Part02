package willydekeyser.youtube_statistics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VideoDetails(String title,
                           String description,
                           String duration,
                           String channelTitle,
                           String publishedAt,
                           long viewCount,
                           long likeCount,
                           long commentCount) {
}
