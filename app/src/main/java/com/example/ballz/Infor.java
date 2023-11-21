package com.example.ballz;

import android.os.Parcel;
import android.os.Parcelable;

public class Infor implements Parcelable {
    private int shots;
    private int shotsOnTarget;
    private int shotsOffTarget;
     private int corners;
    // Add fields for other match statistics



    public Infor(int shots, int shotsOnTarget, int shotsOffTarget, int corners /* Add parameters for other match statistics */) {
        this.shots = shots;
        this.shotsOnTarget = shotsOnTarget;
        this.shotsOffTarget = shotsOffTarget;
        this.corners = corners;
        // Set other fields
    }

    protected Infor(Parcel in) {
        shots = in.readInt();
        shotsOnTarget = in.readInt();
        shotsOffTarget = in.readInt();
        corners = in.readInt();
        // Read other fields from Parcel
    }

    public static final Creator<Infor> CREATOR = new Creator<Infor>() {
        @Override
        public Infor createFromParcel(Parcel in) {
            return new Infor(in);
        }

        @Override
        public Infor[] newArray(int size) {
            return new Infor[size];
        }
    };
    public int getCorners() {
        return corners;
    }

    public void setCorners(int corners) {
        this.corners = corners;
    }
    // Add getters and setters for the fields
    public int getShots() {
        return shots;
    }

    public int getShotsOnTarget() {
        return shotsOnTarget;
    }

    public int getShotsOffTarget() {
        return shotsOffTarget;
    }


    // Add getters and setters for other fields

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(shots);
        dest.writeInt(shotsOnTarget);
        dest.writeInt(shotsOffTarget);
        dest.writeInt(corners);
        // Write other fields to Parcel
    }
}
