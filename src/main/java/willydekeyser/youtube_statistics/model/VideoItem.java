package willydekeyser.youtube_statistics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoItem {
    private Snippet snippet;
    private Statistics statistics;
    private ContentDetails contentDetails;
    public Snippet getSnippet() { return snippet; }
    public Statistics getStatistics() { return statistics; }
    public ContentDetails getContentDetails() { return contentDetails; }

}
