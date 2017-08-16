package com.twitterredesign;

import com.twitterredesign.controller.TwitterController;
import com.twitterredesign.service.TwitterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by tiffany on 6/27/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerTest {

    private MockMvc mockMvc;

    //@InjectMocks
    //private TwitterController controller;

    @Mock
    private TwitterService service;

    @Before
    public void setup() {
        final TwitterController controller = new TwitterController();
        //controller = new TwitterController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void index_ShouldIncludeModels() throws Exception{
        // Arrange the mock behavior
        List<Tweet> timeline = service.getTimeline();
        TwitterProfile yourInfo = service.getUserProfile();
        when(service.getTimeline()).thenReturn(timeline);
        when(service.getUserProfile()).thenReturn(yourInfo);
        // Act and assert results
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/index"));
    }


}
