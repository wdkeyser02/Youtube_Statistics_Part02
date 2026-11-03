package willydekeyser.youtube_statistics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record YouTubeChannelApiResponse(
        List<ChannelItem> items
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ChannelItem(
            String id,
            Snippet snippet,
            ChannelStatistics statistics,
            ContentDetails contentDetails,
            BrandingSettings brandingSettings
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record BrandingSettings(ChannelSettings channel, ImageSettings image) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public record ChannelSettings(String keywords) {}

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record ImageSettings(String bannerExternalUrl) {}
    }
}
