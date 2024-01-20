package com.ahmad.thelemoapp.custmList;

public class customListitems {
    int imgitems;
    String name_fruit;
    int price;
    int price_total;

    public customListitems(int imgitems, String name_fruit, int price, int price_total) {
        this.imgitems = imgitems;
        this.name_fruit = name_fruit;
        this.price = price;
        this.price_total = price_total;
    }

    public int getPrice_total() {
        return price_total;
    }

    public void setPrice_total(int price_total) {
        this.price_total = price_total;
    }

    public int getImgitems() {
        return imgitems;
    }

    public void setImgitems(int imgitems) {
        this.imgitems = imgitems;
    }

    public String getname_fruit() {
        return name_fruit;
    }

    public void setname_fruit(String name_fruit) {
        this.name_fruit = name_fruit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



}
