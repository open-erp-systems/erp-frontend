package com.jukusoft.erp.network.user;

public class Account {

    protected long userID = 0;
    protected String username = "";

    public Account(long userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    public long getUserID () {
        return this.userID;
    }

    public String getUsername () {
        return username;
    }

}
