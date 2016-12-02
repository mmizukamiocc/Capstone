package com.example.mmizukami.capstone;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ethan on 12/1/2016.
 */

// TODO: Send a text message to Mahiro when finished!

public class Relation implements Parcelable {

    protected Relation(Parcel in) {
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
    }
}
