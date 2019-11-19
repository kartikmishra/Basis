package com.example.basis.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MainDataModel implements Parcelable {

    private List<Main> data = null;

    public MainDataModel(Parcel in) {
        data = in.createTypedArrayList(Main.CREATOR);
    }

    public static final Parcelable.Creator<MainDataModel> CREATOR = new Parcelable.Creator<MainDataModel>() {
        @Override
        public MainDataModel createFromParcel(Parcel in) {
            return new MainDataModel(in);
        }

        @Override
        public MainDataModel[] newArray(int size) {
            return new MainDataModel[size];
        }
    };

    public MainDataModel() {

    }

    public List<Main> getData() {
        return data;
    }

    public void setData(List<Main> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(data);
    }
}
