package com.ahmad.thelemoapp.custmList;

public class basketList {
    String nameItems;
    int numitem;
    int price;
int imgItems;
    public basketList(int imgItems, String nameItems, int numitem, int price) {
        this.imgItems = imgItems;
        this.nameItems = nameItems;
        this.numitem = numitem;
        this.price = price;
    }

    public Integer getImgItems() {
        return imgItems;
    }

    public void setImgItems(int imgItems) {
        this.imgItems = imgItems;
    }

    public String getNameItems() {
        return nameItems;
    }

    public void setNameItems(String nameItems) {
        this.nameItems = nameItems;
    }

    public int getNumitem() {
        return numitem;
    }

    public void setNumitem(int numitem) {
        this.numitem = numitem;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
