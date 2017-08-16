package com.twitterredesign.service;

import com.twitterredesign.entity.Reply;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.*;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiffany on 4/26/17.
 */
@Service
public class TwitterService {
    String consumerKey = "JVO3OoIB5zsDnNKnkLzh8lhk2"; // The application's consumer key
    String consumerSecret = "IEFIy7Nr1hPG6PESnRWopBiOGQGKh37qAyL8uAcrzJMjXzdmIq"; // The application's consumer secret
    String accessToken = "37338499-chIFI8QdNoEpWva2YvucvmHFMetNUlPvVdjSmgpe9"; // The access token granted after OAuth authorization
    String accessTokenSecret = "4ItZRy76KaAHgHRodmWIqSS11OiGWtgvCEdA6YFkKB8rS"; // The access token secret granted after OAuth

    Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);

    // Retrieves home timeline (friends'/retweets and user's tweets)
    public List<Tweet> getTimeline() {
        return twitter.timelineOperations().getHomeTimeline();
    }

    //Retrieves authenticated user's profile information
    public TwitterProfile getUserProfile() {
        return twitter.userOperations().getUserProfile();
    }

    //Retrieves authenticated user's screen name
    public String getAuthScreenName() {
        return twitter.userOperations().getScreenName();
    }

    //Retrieves 20 of the authenticated user's tweets
    public List<Tweet> getUsersTweets(){
        return twitter.timelineOperations().getUserTimeline();
    }

    //Retrieves list authenticated user's friends
    public CursoredList<TwitterProfile>getfriends() {
        return twitter.friendOperations().getFriends();
    }

    //Retrieves list authenticated user's followers
    public CursoredList<TwitterProfile>getfollowers() {
        return twitter.friendOperations().getFollowers();
    }

    // Post a tweet
    public Tweet postTweet(String message) {
        return twitter.timelineOperations().updateStatus(message);
    }

    // Reply to a tweet
    public Tweet reply(String message, long id) {
        //TweetData tweetData = new TweetData(message);
        //tweetData.inReplyToStatus(id);
        return twitter.timelineOperations().updateStatus(new TweetData(message).inReplyToStatus(id));
    }

    // Retrieves other profile information by id
    public TwitterProfile getAnyProfile(String screenName) {
        return twitter.userOperations().getUserProfile(screenName);
    }

    // Retrieves other user's timeline first 20
    public List<Tweet> getAnyUserTimeline(String screenName) {
        return twitter.timelineOperations().getUserTimeline(screenName);
    }

    // Searches for profiles up to 20 matches
    public List<TwitterProfile> searchProfiles(String user) {
        return twitter.userOperations().searchForUsers(user);
    }

    //search twitter for tweets
    public List<Tweet> searchTwitter(String query, int size) {
        //List<SearchResults> results = new ArrayList<>();
        return twitter.searchOperations().search(query, size).getTweets();
    }

    // send a direct message
    public DirectMessage sendDM(Reply reply) {
       return twitter.directMessageOperations().sendDirectMessage(reply.getStrTweetId(), reply.getMessage());
    }

    // deletes a status update
    public void delete(String tweetId) {
        long l = Long.parseLong(tweetId);
        twitter.timelineOperations().deleteStatus(l);
    }

    public void retweet(String s) {
        long l = Long.parseLong(s);
        twitter.timelineOperations().retweet(l);
    }

    public void addToFavs(String s) {
        long l = Long.parseLong(s);
        twitter.timelineOperations().addToFavorites(l);
    }

    public List<Tweet> getfavorites() {
        return twitter.timelineOperations().getFavorites();
    }

    public List<DirectMessage> getDMsRecieved() {
        return twitter.directMessageOperations().getDirectMessagesReceived();
    }

    public String followUser(String name) {
        return twitter.friendOperations().follow(name);
    }

    public String unfollowUser(String name) {
        return twitter.friendOperations().unfollow(name);
    }

}