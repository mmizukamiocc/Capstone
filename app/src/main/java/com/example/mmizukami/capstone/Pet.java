package com.example.mmizukami.capstone;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by mmizukami on 11/10/2016.
 */

public class Pet implements Parcelable{
    private int mId;
    private String mPetName;
    private String mType;
    private String mDescription;
    private boolean mAdoption;
    private boolean mLost;
    private Uri mImageUri;
    private Context mContext;
    /**
     * Constructor for Pet object
     * all param can not be @code null
     * @param mId id for the Pet
     * @param mPetName name of the pet
     * @param mType type of the pet
     * @param mDescription description of the pet
     * @param mAdoption this member variable will be true if the pet is for adoption
     * @param mLost this member variable will be true if the pet is lost
     * @param mImageUri URI for the pet image
     *
     * @author Mahiro Mizukami
     * */
    public Pet(int mId,  String mPetName, String mType, String mDescription, boolean mAdoption, boolean mLost, Uri mImageUri) {
        this.mId = mId;

        this.mPetName = mPetName;
        this.mType = mType;
        this.mDescription = mDescription;
        this.mAdoption = mAdoption;
        this.mLost = mLost;
        this.mImageUri = mImageUri;
    }

    /**
     * Constructor for Pet object with default value
     * @author Mahiro Mizukami
     * */
    public Pet() {
        mId = -1;

        mType = "none";
        mDescription = "";
        mAdoption = false;
        mLost = false;
        mImageUri = defaultImageUri();
    }

    /**
    * test constructor with default image
    *
    * */
    public Pet( String mPetName, String mType, String mDescription, boolean mAdoption, boolean mLost) {


        this.mPetName = mPetName;
        this.mType = mType;
        this.mDescription = mDescription;
        this.mAdoption = mAdoption;
        this.mLost = mLost;
        this.mImageUri = defaultImageUri();
    }

    /**
     * Constructor for Pet object without id - it is usually for Database
     * all param can not be @code null
     * @param mPetName name of the pet
     * @param mType type of the pet
     * @param mDescription description of the pet
     * @param mAdoption this member variable will be true if the pet is for adoption
     * @param mLost this member variable will be true if the pet is lost
     * @param mImageUri URI for the pet image
     *
     * @author Mahiro Mizukami
     * */
    public Pet(String mPetName, String mType, String mDescription, boolean mAdoption, boolean mLost, Uri mImageUri) {
        mId = -1;
        this.mPetName = mPetName;
        this.mType = mType;
        this.mDescription = mDescription;
        this.mAdoption = mAdoption;
        this.mLost = mLost;
        this.mImageUri = mImageUri;
    }

    /**
     * Constructor for Pet object for parcelable extra in intent
     * @param in Parcel data in Intent
     * @author Mahiro Mizukami
     * */
    protected Pet(Parcel in) {
        mId = in.readInt();

        mPetName = in.readString();
        mType = in.readString();
        mDescription = in.readString();
        mAdoption = in.readByte() != 0;
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
    /**
     * Getter for mId
     * @return int ID for the pet
     * @author Mahiro Mizukami
     * */
    public int getId() {
        return mId;
    }

    /**
     * Getter for mPetName
     * @return String name of the pet
     * @author Mahiro Mizukami
     * */
    public String getPetName() {
        return mPetName;
    }

    /**
     * Setter for mPetName
     * @param mPetName new name for the pet
     * @author Mahiro Mizukami
     * */
    public void setPetName(String mPetName) {
        this.mPetName = mPetName;
    }

    /**
     * Getter for mType
     * @return String type of the pet
     * @author Mahiro Mizukami
     * */
    public String getType() {
        return mType;
    }

    /**
     * Setter for mType
     * @param mType new type for the pet
     * @author Mahiro Mizukami
     * */
    public void setType(String mType) {
        this.mType = mType;
    }

    /**
     * Getter for mDescription
     * @return String description for the pet
     * @author Mahiro Mizukami
     * */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Setter for mDescription
     * @param mDescription new description for the pet
     * @author Mahiro Mizukami
     * */
    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    /**
     * Getter for mAdoption
     * @return boolean adoption status for the pet
     * @author Mahiro Mizukami
     * */
    public boolean isAdopted() {
        return mAdoption;
    }

    /**
     * Setter for mAdoption
     * @param mAdoption new adoption status for the pet
     * @author Mahiro Mizukami
     * */
    public void setAdoption(boolean mAdoption) {
        this.mAdoption = mAdoption;
    }

    /**
     * Getter for mLost
     * @return boolean lost status for the pet
     * @author Mahiro Mizukami
     * */
    public boolean isLost() {
        return mLost;
    }

    /**
     * Setter for mLost
     * @param mLost new lost status for the pet
     * @author Mahiro Mizukami
     * */
    public void setLost(boolean mLost) {
        this.mLost = mLost;
    }

    /**
     * Getter for mImageUri
     * @return Uri Image Uri for the picture of the pet
     * @author Mahiro Mizukami
     * */
    public Uri getImageUri() {
        return mImageUri;
    }

    /**
     * Setter for mImageUri
     * @param mImageUri new image URI for the pet
     * @author Mahiro Mizukami
     * */
    public void setImageUri(Uri mImageUri) {
        this.mImageUri = mImageUri;
    }

    /**
     * Default value for mImageUri
     * @author Mahiro Mizukami
     * */
    public Uri defaultImageUri() throws Resources.NotFoundException {
        mContext =  MainActivity.getInstance().getApplicationContext();
        Resources res = mContext.getResources();

        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
        "://" + res.getResourcePackageName(R.drawable.dog)
        + '/' + res.getResourceTypeName(R.drawable.dog)
        + '/' + res.getResourceEntryName(R.drawable.dog));
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
        parcel.writeByte((byte) (mAdoption ? 1 : 0));
        parcel.writeByte((byte) (mLost ? 1 : 0));
        parcel.writeParcelable(mImageUri, i);
    }
}
