package com.ahmad.thelemoapp.custmList;

public class listviewpagerMain {
    String typefruit;
    int img_res;

    public listviewpagerMain(String typefruit, int img_res) {
        this.typefruit = typefruit;
        this.img_res = img_res;
    }

    public String getTypefruit() {
        return typefruit;
    }

    public void setTypefruit(String typefruit) {
        this.typefruit = typefruit;
    }

    public int getImg_res() {
        return img_res;
    }

    public void setImg_res(int img_res) {
        this.img_res = img_res;
    }
}
