package willydekeyser.youtube_statistics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ChannelStatistics(
        Long viewCount,
        Long subscriberCount,
        Boolean hiddenSubscriberCount,
        Long videoCount
) {
}
