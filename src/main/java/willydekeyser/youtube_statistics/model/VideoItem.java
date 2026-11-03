package willydekeyser.youtube_statistics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VideoItem (
    Snippet snippet,
    Statistics statistics,
    ContentDetails contentDetails) {

}
