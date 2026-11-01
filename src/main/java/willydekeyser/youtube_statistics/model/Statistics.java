package willydekeyser.youtube_statistics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Statistics {
    private String viewCount;
    private String likeCount;
    private String commentCount;
    public String getViewCount() { return viewCount; }
    public String getLikeCount() { return likeCount; }
    public String getCommentCount() { return commentCount; }

}
