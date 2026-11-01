package willydekeyser.youtube_statistics.model;

public record VideoDetails(String title,
                           String description,
                           String duration,
                           String channelTitle,
                           String publishedAt,
                           long viewCount,
                           long likeCount,
                           long commentCount) {
}
