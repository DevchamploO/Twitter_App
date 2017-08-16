package com.twitterredesign.entity;

/**
 * Created by tiffany on 5/8/17.
 */
public class Reply {

    private String tweetId;
    private String message;

    public Reply(String tweetId, String message) {
        this.tweetId = tweetId;
        this.message = message;
    }

    public Reply() {
    }

    public long getTweetId() {
        return Long.parseLong(tweetId);
    }

    public String getStrTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
