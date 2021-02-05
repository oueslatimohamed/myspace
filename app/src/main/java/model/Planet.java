package model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

public class Planet implements Parcelable {
    private int id;
    private String mName;
    private String mDescription;
    private String mColor;
    private int mImageurl;

    public Planet() {
    }

    public Planet(int id, String mName, String mDescription, String mColor, int mImageurl) {
        this.id = id;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mColor = mColor;
        this.mImageurl = mImageurl;
    }


    protected Planet(Parcel in) {
        id = in.readInt();
        mName = in.readString();
        mDescription = in.readString();
        mColor = in.readString();
        mImageurl = in.readInt();
    }

    public static final Creator<Planet> CREATOR = new Creator<Planet>() {
        @Override
        public Planet createFromParcel(Parcel in) {
            return new Planet(in);
        }

        @Override
        public Planet[] newArray(int size) {
            return new Planet[size];
        }
    };

    public String getmColor() {
        return mColor;
    }

    public void setmColor(String mColor) {
        this.mColor = mColor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getmImageurl() {
        return mImageurl;
    }

    public void setmImageurl(int mImageurl) {
        this.mImageurl = mImageurl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(mName);
        parcel.writeString(mDescription);
        parcel.writeString(mColor);
        parcel.writeInt(mImageurl);
    }
}
