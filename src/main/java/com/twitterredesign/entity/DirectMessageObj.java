package com.twitterredesign.entity;

/**
 * Created by tiffany on 6/15/17.
 */
public class DirectMessageObj {

    String screenName;
    String message;

    public DirectMessageObj(String screenName, String message) {
        this.screenName = screenName;
        this.message = message;
    }

    public DirectMessageObj() {
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
