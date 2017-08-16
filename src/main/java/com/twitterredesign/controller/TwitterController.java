package com.twitterredesign.controller;


import com.twitterredesign.entity.DirectMessageObj;
import com.twitterredesign.entity.Reply;
import com.twitterredesign.entity.TweetObj;
import com.twitterredesign.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tiffany on 4/26/17.
 */

@Controller
//@RequestMapping(value = "/home")
public class TwitterController {

   // public static final String TWITTER_BASE_URI = "svc/v1/tweets";

    @Autowired
    TwitterService twitterService;

    // Gets the authenticated user's details this persists across pages
    public void getAuthUserDetails(Model m) {
        TwitterProfile yourInfo = twitterService.getUserProfile();
        m.addAttribute("profileImg", yourInfo.getProfileImageUrl());
        m.addAttribute("profileName", twitterService.getAuthScreenName());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUserProfile(Model model) {
        List<Tweet> homeTimeline = twitterService.getTimeline();
        TwitterProfile yourInfo = twitterService.getUserProfile();
        getAuthUserDetails(model);
        model.addAttribute("user", yourInfo);
        model.addAttribute("results", homeTimeline);
        return "pages/index";
    }

    //@ResponseBody
    @RequestMapping(value = "/timeline", method = RequestMethod.GET)
    public List<Tweet> getHomeTimeline() {
        return twitterService.getTimeline();
    }

    /*@ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String retweet(@RequestBody String id) {
        twitterService.retweet(id);
        return "/";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String retweet(@PathVariable long id) {
        twitterService.retweet(id);
        return "/";
    }*/

    // Gets user profile info by screen name
    @RequestMapping(value = "/{fromUser}", method = RequestMethod.GET)
    public String requestProfileDetails(@PathVariable String fromUser, Model model) {
        //TwitterProfile yourInfo = twitterService.getUserProfile();
        //model.addAttribute("profileImg", yourInfo.getProfileImageUrl());
        getAuthUserDetails(model);
        TwitterProfile userInfo = twitterService.getAnyProfile(fromUser);
        List<Tweet> userTimeline = twitterService.getAnyUserTimeline(fromUser);
        model.addAttribute("user", userInfo);
        model.addAttribute("results", userTimeline);
        return "pages/index";
    }

   /* @RequestMapping(value="", method = RequestMethod.GET)
    public String getTest(Model model) {
        model.addAttribute("title", "This is a test");
        return "pages/test";
    }*/

    @RequestMapping(value = "/test/{user}", method = RequestMethod.GET)
    @ResponseBody
    public TwitterProfile startTest(@PathVariable String user) {
        TwitterProfile userInfo = twitterService.getAnyProfile(user);
        return userInfo;
    }

    @RequestMapping(value = "/test/home", method = RequestMethod.GET)
    @ResponseBody
    public TwitterProfile startTest() {
        TwitterProfile userInfo = twitterService.getUserProfile();
        return userInfo;
    }





    @RequestMapping(value = "/api/reply", method = RequestMethod.POST)
    public String replytoTweet(@RequestBody Reply reply){
        twitterService.reply(reply.getMessage(), reply.getTweetId());
        System.out.println(reply.getMessage());
        return "/";
    }

    @ResponseBody
    @RequestMapping(value = "/api/retweet/{tweetId}", method = RequestMethod.POST)
    public void makeRetweet(@PathVariable String tweetId) {
        twitterService.retweet(tweetId);
    }

    @RequestMapping(value = "/api/direct_message", method = RequestMethod.POST)
    public void directMessage(@RequestBody Reply reply) {
        twitterService.sendDM(reply);
        //return "pages/index";
    }

    @ResponseBody
    @RequestMapping(value = "/api/delete/{id}", method = RequestMethod.POST)
    public void deleteStatus(@PathVariable String id) {
        twitterService.delete(id);
    }

    @RequestMapping(value = "/api/like/{id}", method = RequestMethod.POST)
    public void makeLike(@PathVariable String id) {
        twitterService.addToFavs(id);
    }

    //@ResponseBody
    /*@RequestMapping(value = "/api/tweets", method = RequestMethod.GET)
    public List<Tweet> apiList() {
        List<Tweet> alist = twitterService.getTimeline();
        return alist;
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postTest(@RequestParam String tweet, Model model) {
        model.addAttribute("show", tweet);
        List<SearchResults> search =
        return "pages/test";
    }*/


}
