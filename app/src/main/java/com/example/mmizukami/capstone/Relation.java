package com.example.mmizukami.capstone;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ethan on 12/1/2016.
 */

// TODO: Add testing cases

public class Relation implements Parcelable {

    private int relationId;
    private Pet mPet;
    private User mUser;

    public Relation(int relation, Pet petToAdd, User userToAdd) {
        relationId = relation;
        mPet = petToAdd;
        mUser = userToAdd;
    }
    public Relation( Pet petToAdd, User userToAdd) {
        relationId = -1;
        mPet = petToAdd;
        mUser = userToAdd;
    }
    public Pet getPet() {
        return mPet;
    }

    public User getUser() {
        return mUser;
    }

    public void setPet(Pet pet) {
        mPet = pet;
    }

    public void setUser(User user) {
        mUser = user;
    }

    protected Relation(Parcel in) {
        relationId = in.readInt();
        mPet = in.readParcelable(Pet.class.getClassLoader());
        mUser = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<Relation> CREATOR = new Creator<Relation>() {
        @Override
        public Relation createFromParcel(Parcel in) {
            return new Relation(in);
        }

        @Override
        public Relation[] newArray(int size) {
            return new Relation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(relationId);
        parcel.writeParcelable(mPet, 0);
        parcel.writeParcelable(mUser, 0);
    }
}
