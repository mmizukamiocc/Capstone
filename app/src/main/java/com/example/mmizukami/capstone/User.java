package com.example.mmizukami.capstone;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mmizukami on 11/10/2016.
 */

public class User implements Parcelable{
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

    protected User(Parcel in) {
        mId = in.readInt();
        mUserName = in.readString();
        mRealName = in.readString();
        mEmail = in.readString();
        mPhone = in.readString();
        mPassword = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mUserName);
        dest.writeString(mRealName);
        dest.writeString(mEmail);
        dest.writeString(mPhone);
        dest.writeString(mPassword);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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
