package willydekeyser.youtube_statistics.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import willydekeyser.youtube_statistics.model.YouTubeChannelApiResponse;
import willydekeyser.youtube_statistics.model.YouTubeVideoApiResponse;
import willydekeyser.youtube_statistics.service.YouTubeService;

@RestController
public class YouTubeController {

    private final YouTubeService youTubeService;

    public YouTubeController(YouTubeService youTubeService) {
        this.youTubeService = youTubeService;
    }

    @GetMapping("/api/video-info") // http://localhost:8080/api/video-info?videoId=vRQgnSHaGak
    public YouTubeVideoApiResponse.VideoItem getVideoInfo(@RequestParam String videoId) {

        return youTubeService.getVideoDetails(videoId);
    }

    @GetMapping("/api/channel-info") // http://localhost:8080/api/channel-info?channelId=UClrC0HaC7gJHkLS9VJyz4SA
    public YouTubeChannelApiResponse.ChannelItem getChannelInfo(@RequestParam String channelId) {
        return youTubeService.getChannelDetails(channelId);
    }
}