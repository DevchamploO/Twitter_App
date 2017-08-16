package com.twitterredesign.controller;

import com.twitterredesign.entity.TweetObj;
import com.twitterredesign.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by tiffany on 6/20/17.
 */

@Controller
public class HeaderController {

    @Autowired
    TwitterService twitterService;

    public void getAuthUserDetails(Model m) {
        TwitterProfile yourInfo = twitterService.getUserProfile();
        m.addAttribute("profileImg", yourInfo.getProfileImageUrl());
        m.addAttribute("profileName", twitterService.getAuthScreenName());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "action=compose")
    public String save(TweetObj tweetObj, Model model) {
        //model.addAttribute("show", tweetObj.getTweet());
        twitterService.postTweet(tweetObj.getTweet());
        return "redirect:";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "action=search")
    public String search(TweetObj tweetObj, Model model) {
        List<TwitterProfile> userResults = twitterService.searchProfiles(tweetObj.getTweet());
        model.addAttribute("userResults", userResults);
        return "pages/results";

    }

    /* menu options */

    // show authenticated user's tweets
    @RequestMapping(value = "about")
    public String aboutPAge() {
        return "pages/about";
    }

    @RequestMapping(value = "/tweets", method = RequestMethod.GET)
    public String getUserTweets(Model m) {
        getAuthUserDetails(m);
        List<Tweet> authUserTimeline = twitterService.getUsersTweets();
        m.addAttribute("results",authUserTimeline);
        return "pages/index";
    }

    // Shows authenticated user's friends
    @RequestMapping(value = "/following", method = RequestMethod.GET)
    public String displayFriends(Model m) {
        getAuthUserDetails(m);
        CursoredList<TwitterProfile> friends = twitterService.getfriends();
        m.addAttribute("results", friends);
        return "pages/listpage";
    }

    // Shows authenticated user's followers
    @RequestMapping(value = "/followers", method = RequestMethod.GET)
    public String displayFollowers(Model m) {
        getAuthUserDetails(m);
        CursoredList<TwitterProfile> friends = twitterService.getfollowers();
        m.addAttribute("results", friends);
        return "pages/listpage";
    }

    @RequestMapping(value = "/likes", method = RequestMethod.GET)
    public String displaylikes(Model m) {
        getAuthUserDetails(m);
        List<Tweet> likes = twitterService.getfavorites();
        m.addAttribute("results", likes);
        return "pages/listpage2";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String displayMessagesRecieved(Model m) {
        getAuthUserDetails(m);
        List<DirectMessage> dm = twitterService.getDMsRecieved();
        m.addAttribute("results", dm);
        return "pages/messages";
    }
}
