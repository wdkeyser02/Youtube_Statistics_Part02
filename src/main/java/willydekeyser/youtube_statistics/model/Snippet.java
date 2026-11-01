package willydekeyser.youtube_statistics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Snippet {
    private String title;
    private String description;
    private String publishedAt;
    private String channelTitle;
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPublishedAt() { return publishedAt; }
    public String getChannelTitle() { return channelTitle; }

}
