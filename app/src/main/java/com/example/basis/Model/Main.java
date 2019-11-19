package com.example.basis.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Main implements Parcelable {


    private String id;
    private String text;

    public Main(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public Main(Parcel in) {
        id = in.readString();
        text = in.readString();
    }

    public static final Parcelable.Creator<Main> CREATOR = new Parcelable.Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel in) {
            return new Main(in);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };

    public Main() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(text);
    }
}
