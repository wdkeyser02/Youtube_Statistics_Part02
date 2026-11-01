package willydekeyser.youtube_statistics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YouTubeResponse {
    private List<VideoItem> items;
    public List<VideoItem> getItems() { return items; }
}

