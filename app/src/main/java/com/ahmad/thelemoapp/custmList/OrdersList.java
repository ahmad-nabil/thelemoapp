package com.ahmad.thelemoapp.custmList;

public class OrdersList {
String Orderid;
int total_price;

    public OrdersList(String orderid, int total_price) {
        Orderid = orderid;
        this.total_price = total_price;
    }

    public String getOrderid() {
        return Orderid;
    }

    public void setOrderid(String orderid) {
        Orderid = orderid;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}
