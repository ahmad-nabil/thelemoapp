package com.ahmad.thelemoapp.GenratorIDorder;

import java.util.Random;

public class genrator {
    public String getProducts(){
        String products="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder productcode=new StringBuilder();
        Random random=new Random();
        for (int lengthChar = 0; lengthChar < 6; lengthChar++) {
            int index = random.nextInt(products.length());
            productcode.append(products.charAt(index));
        }
return productcode.toString();
    }

}
