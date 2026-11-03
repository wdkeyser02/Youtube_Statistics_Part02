package willydekeyser.youtube_statistics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record YouTubeChannelApiResponse(
        List<ChannelItem> items
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ChannelItem(
            String kind,
            String etag,
            String id,
            Snippet snippet,
            ChannelStatistics statistics,
            ContentDetails contentDetails,
            BrandingSettings brandingSettings
    ) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        private record Snippet(
                String title,
                String description,
                String customUrl,
                String publishedAt,
                Thumbnails thumbnails,
                String defaultLanguage,
                Localized localized,
                String country) {

            @JsonIgnoreProperties(ignoreUnknown = true)
            private record Thumbnails (
                    Medium medium) {
                @JsonIgnoreProperties(ignoreUnknown = true)
                private record Medium (
                        String url,
                        Integer width,
                        Integer height) {}
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            private record Localized (
                    String title,
                    String description) {}
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        private record ChannelStatistics(
                Long viewCount,
                Long subscriberCount,
                Boolean hiddenSubscriberCount,
                Long videoCount
        ) {}

        @JsonIgnoreProperties(ignoreUnknown = true)
        private record ContentDetails (
                RelatedPlaylists relatedPlaylists) {
            @JsonIgnoreProperties(ignoreUnknown = true)
            private record RelatedPlaylists (
                    String likes,
                    String favorites,
                    String uploads) {}
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        private record BrandingSettings(
                ChannelSettings channel,
                Watch watch) {

            @JsonIgnoreProperties(ignoreUnknown = true)
            private record ChannelSettings(
                    String title,
                    String description,
                    String keywords,
                    String trackingAnalyticsTrailer,
                    String defaultLanguage,
                    String country) {}

            @JsonIgnoreProperties(ignoreUnknown = true)
            private record Watch(
                    String textColor,
                    String backgroundColor,
                    String featuredPlaylistId) {}
        }
    }

}
