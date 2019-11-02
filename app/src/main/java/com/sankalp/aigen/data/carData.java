package com.sankalp.aigen.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class carData {
    public carData() {
    }

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "seller_Name")
    public String sellerName;
    @ColumnInfo(name = "car_Name")
    public String carName;
    @ColumnInfo(name = "car_Price")
    public String carPrice;
    @ColumnInfo(name = "car_Seats")
    public String carSeats;
    @ColumnInfo(name = "car_Company")
    public String carCompany;
    @ColumnInfo(name = "seller_Contact")
    public String sellerContact;
    @ColumnInfo (name = "car_Pic_Url")
    public String carPicUrl;

    public String getCarPicUrl() {
        return carPicUrl;
    }

    public void setCarPicUrl(String carPicUrl) {
        this.carPicUrl = carPicUrl;
    }

    public carData(String sellerName, String carName, String carPrice, String carSeats, String carCompany, String sellerContact, String carPicUrl) {
        this.sellerName = sellerName;
        this.carName = carName;
        this.carPrice = carPrice;
        this.carSeats = carSeats;
        this.carCompany = carCompany;
        this.sellerContact = sellerContact;
        this.carPicUrl = carPicUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarSeats() {
        return carSeats;
    }

    public void setCarSeats(String carSeats) {
        this.carSeats = carSeats;
    }

    public String getCarCompany() {
        return carCompany;
    }

    public void setCarCompany(String carCompany) {
        this.carCompany = carCompany;
    }

    public String getSellerContact() {
        return sellerContact;
    }

    public void setSellerContact(String sellerContact) {
        this.sellerContact = sellerContact;
    }


}
