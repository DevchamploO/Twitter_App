package com.twitterredesign;

import com.twitterredesign.controller.HeaderController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by tiffany on 6/27/17.
 */
public class HeaderControllerTest {

    private MockMvc mockMvc;
    private HeaderController controller;

    @Before
    public void setup() {
        controller = new HeaderController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void home_ShouldRenderIndexView() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/about"));
    }

}
