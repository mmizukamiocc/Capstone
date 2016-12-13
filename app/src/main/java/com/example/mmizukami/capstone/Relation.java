package com.example.mmizukami.capstone;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ethan on 12/1/2016.
 */

public class Relation implements Parcelable {

    private int relationId;
    private Pet mPet;
    private User mUser;

    /**
    * 'cnstructor for Relation object.
    * Takes in an int for the relationId,
    * a Pet for the mPet field, and a User for the mUser field.
    * Primarily used to inflate detail layout.
    *
    * @author Ethan Quach
    */
    
    public Relation(int relation, Pet petToAdd, User userToAdd) {
        relationId = relation;
        mPet = petToAdd;
        mUser = userToAdd;
    }
    
    /**
    * Alternative constructor for Relation object.
    * Takes in a Pet for the mPet field and a User for the mUser field.
    * Used for when a user adds a pet to the database.
    * 
    * @Author Ethan Quach
    */
    public Relation(Pet petToAdd, User userToAdd) {
        relationId = -1;
        mPet = petToAdd;
        mUser = userToAdd;
    }
    
    /**
    * Getter for mPet.
    * @return Pet associated with Relation object
    *
    * @Aathor Ethan Quach
    */
    public Pet getPet() {
        return mPet;
    }

    /**
    * Getter for mUser.
    * @return User associated with Relation object
    *
    * @author Ethan Quach
    */
    public User getUser() {
        return mUser;
    }

    /**
    * Setter for mPet.
    * @param pet The pet to be associated with the Relation object
    *
    * @author Ethan Quach
    */
    public void setPet(Pet pet) {
        mPet = pet;
    }

    /**
    * Setter for mUser.
    * @param user The user to be associated with the Relation object
    *
    * @author Ethan Quach
    */
    public void setUser(User user) {
        mUser = user;
    }

    /**
    * Overloaded constructor for Relation, for use in Intents.
    * Takes a Parcel object holding a Pet and constructs it.
    * @param in The Parcel to be built.
    *
    * @author Ethan Quach
    */
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
