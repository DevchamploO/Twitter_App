package com.twitterredesign.entity;

/**
 * Created by tiffany on 5/4/17.
 */

public class TweetObj {

    private String tweet;

    public TweetObj(String tweet) {
        this.tweet = tweet;
    }

    public TweetObj() {
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }
}
