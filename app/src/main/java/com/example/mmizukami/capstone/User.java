package com.example.mmizukami.capstone;

/**
 * Created by mmizukami on 11/10/2016.
 */

public class User {
    private int mId;
    private String mUserName;
    private String mRealName;
    private String mEmail;
    private String mPhone;
    private String mPassword;

    public User()
    {
        mId = -1;
        mUserName = "";
        mRealName = "";
        mEmail = "";
        mPhone = "";
        mPassword = "";

    }

    public User(int mId, String mUserName, String mRealName, String mEmail, String mPhone, String mPassword) {
        this.mId = mId;
        this.mUserName = mUserName;
        this.mRealName = mRealName;
        this.mEmail = mEmail;
        this.mPhone = mPhone;
        this.mPassword = mPassword;
    }

    public int getId() {
        return mId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getRealName() {
        return mRealName;
    }

    public void setRealName(String mRealName) {
        this.mRealName = mRealName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }


}
