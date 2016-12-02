package com.example.mmizukami.capstone;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mmizukami on 11/10/2016.
 */

public class Pet implements Parcelable{
    private int mId;
    private String mPetName;
    private String mType;
    private String mDescription;
    private boolean mAdaption;
    private boolean mLost;
    private Uri mImageUri;


    public Pet(int mId,  String mPetName, String mType, String mDescription, boolean mAdaption, boolean mLost, Uri mImageUri) {
        this.mId = mId;

        this.mPetName = mPetName;
        this.mType = mType;
        this.mDescription = mDescription;
        this.mAdaption = mAdaption;
        this.mLost = mLost;
        this.mImageUri = mImageUri;
    }

    public Pet() {
        mId = -1;

        mType = "none";
        mDescription = "";
        mAdaption = false;
        mLost = false;
        mImageUri = null; // set something as Default later
    }

    protected Pet(Parcel in) {
        mId = in.readInt();

        mPetName = in.readString();
        mType = in.readString();
        mDescription = in.readString();
        mAdaption = in.readByte() != 0;
        mLost = in.readByte() != 0;
        mImageUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Pet> CREATOR = new Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };

    public int getId() {
        return mId;
    }

    public String getPetName() {
        return mPetName;
    }

    public void setPetName(String mPetName) {
        this.mPetName = mPetName;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public boolean isAdaption() {
        return mAdaption;
    }

    public void setAdaption(boolean mAdaption) {
        this.mAdaption = mAdaption;
    }

    public boolean isLost() {
        return mLost;
    }

    public void setLost(boolean mLost) {
        this.mLost = mLost;
    }

    public Uri getImageUri() {
        return mImageUri;
    }

    public void setImageUri(Uri mImageUri) {
        this.mImageUri = mImageUri;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mPetName);
        parcel.writeString(mType);
        parcel.writeString(mDescription);
        parcel.writeByte((byte) (mAdaption ? 1 : 0));
        parcel.writeByte((byte) (mLost ? 1 : 0));
        parcel.writeParcelable(mImageUri, i);
    }
}
