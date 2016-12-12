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


    /**
     * Constructor for User object with default value
     * @author Mahiro Mizukami
     * */
    public User()
    {
        mId = -1;
        mUserName = "";
        mRealName = "";
        mEmail = "";
        mPhone = "";
        mPassword = "";

    }

    /**
     * Constructor for User object
     * all param can not be @code null
     * @param mId id for the user
     * @param mUserName username of the user
     * @param mRealName real name of the user
     * @param mEmail Email address of the user
     * @param mPhone phone number of the user
     * @param mPassword password of the user account
     *
     * @author Mahiro Mizukami
     * */
    public User(int mId, String mUserName, String mRealName, String mEmail, String mPhone, String mPassword) {
        this.mId = mId;
        this.mUserName = mUserName;
        this.mRealName = mRealName;
        this.mEmail = mEmail;
        this.mPhone = mPhone;
        this.mPassword = mPassword;
    }

    /**
     * Constructor for User object without Id, it is for database
     * all param can not be @code null
     * @param mUserName username of the user
     * @param mRealName real name of the user
     * @param mEmail Email address of the user
     * @param mPhone phone number of the user
     * @param mPassword password of the user account
     *
     * @author Mahiro Mizukami
     * */
    public User(String mUserName, String mRealName, String mEmail, String mPhone, String mPassword) {
        this.mId = -1;
        this.mUserName = mUserName;
        this.mRealName = mRealName;
        this.mEmail = mEmail;
        this.mPhone = mPhone;
        this.mPassword = mPassword;
    }

    /**
     * Constructor for User object for parcelable extra in intent
     * @param in Parcel data in Intent
     * @author Mahiro Mizukami
     * */
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

    /**
     * Getter for mId
     * @return int ID for the user
     * @author Mahiro Mizukami
     * */
    public int getId() {
        return mId;
    }

    /**
     * Getter for mUserName
     * @return String username for the user
     * @author Mahiro Mizukami
     * */
    public String getUserName() {
        return mUserName;
    }

    /**
     * Setter for mUserName
     * @param mUserName new user name for the User
     * @author Mahiro Mizukami
     * */
    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    /**
     * Getter for mRealName
     * @return String real name for the user
     * @author Mahiro Mizukami
     * */
    public String getRealName() {
        return mRealName;
    }

    /**
     * Setter for mRealName
     * @param mRealName new real name for the user
     * @author Mahiro Mizukami
     * */
    public void setRealName(String mRealName) {
        this.mRealName = mRealName;
    }

    /**
     * Getter for mEmail
     * @return String Email for the user
     * @author Mahiro Mizukami
     * */
    public String getEmail() {
        return mEmail;
    }


    /**
     * Setter for mEmail
     * @param mEmail new Email address for the user
     * @author Mahiro Mizukami
     * */
    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    /**
     * Getter for mPhone
     * @return String phone number for the user
     * @author Mahiro Mizukami
     * */
    public String getPhone() {
        return mPhone;
    }

    /**
     * Setter for mPhone
     * @param mPhone new phone number for the user
     * @author Mahiro Mizukami
     * */
    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    /**
     * Getter for mPassword
     * @return String password for the user
     * @author Mahiro Mizukami
     * */
    public String getPassword() {
        return mPassword;
    }

    /**
     * Setter for mPassword
     * @param mPassword new password for the user
     * @author Mahiro Mizukami
     * */
    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }


}
