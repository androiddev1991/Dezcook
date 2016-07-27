package com.example.android.dezcook.Activities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Android on 6/11/2016.
 */
public class Food implements Parcelable {
    protected int txtid;
    protected String txtName;
    protected String txtDesc;
    protected String txtImage;
    protected boolean txtFav;
    protected String items;
    protected int converted_image;
    public int getTxtid() {
        return txtid;
    }
    public void setTxtid(int txtid) {
        this.txtid = txtid;
    }
    public String getTxtName() {
        return txtName;
    }
    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }
    public String getTxtDesc() {
        return txtDesc;
    }
    public void setTxtDesc(String txtDesc) {
        this.txtDesc = txtDesc;
    }
    public String getTxtImage() {
        return txtImage;
    }
    public void setTxtImage(String txtImage) {
        this.txtImage = txtImage;
    }
    public boolean isTxtFav() {
        return txtFav;
    }
    public void setTxtFav(boolean txtFav) {
        this.txtFav = txtFav;
    }
    public String getItems() {
        return items;
    }
    public void setItems(String items) {
        this.items = items;
    }
    public int getConverted_image() {
        return converted_image;
    }
    public void setConverted_image(int converted_image) {
        this.converted_image = converted_image;
    }
public Food(){}
    protected Food(Parcel in) {
        txtid = in.readInt();
        txtName = in.readString();
        txtDesc = in.readString();
        txtImage = in.readString();
        txtFav = in.readByte() != 0;
        items = in.readString();
        converted_image = in.readInt();
    }
    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(txtid);
        dest.writeString(txtName);
        dest.writeString(txtDesc);
        dest.writeString(txtImage);
        dest.writeByte((byte) (txtFav ? 1 : 0));
        dest.writeString(items);
        dest.writeInt(converted_image);
    }
}
