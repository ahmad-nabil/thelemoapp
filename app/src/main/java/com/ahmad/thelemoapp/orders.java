package com.ahmad.thelemoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ahmad.thelemoapp.adabter.adabterorder;
import com.ahmad.thelemoapp.custmList.OrdersList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class orders extends AppCompatActivity implements View.OnClickListener {
Gson gson=new Gson();
String ordersList;
SharedPreferences getorderslist;
ArrayList <OrdersList> Orders;
RecyclerView orderRecycle;
adabterorder adapter;
View CustomMenu;
Animation popup,popdown;
FrameLayout framOrders;
    Button backbtn_order,menuBtn_order,account_btn,orders_btn,logout_btn,basket_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
    getorderslist=getSharedPreferences("arrays",MODE_PRIVATE);
//check orders coming from baskets
 ordersList =getorderslist.getString("orderlist",null);
    if (ordersList !=null){
        Type type=new TypeToken<ArrayList<OrdersList>>(){}.getType();
    Orders=gson.fromJson(ordersList,type);
    }else {
        Orders=new ArrayList<>();
        Toast.makeText(this, "you dont have any order", Toast.LENGTH_SHORT).show();
    }

    orderRecycle=findViewById(R.id.orderRecycle);
    adapter=new adabterorder(orders.this,Orders);
    orderRecycle.setAdapter(adapter);
    orderRecycle.setLayoutManager(new GridLayoutManager(orders.this,2));
    //custom menu and other button
        menuBtn_order=findViewById(R.id.menuBtn_order);
        backbtn_order=findViewById(R.id.backbtn_order);
        framOrders=findViewById(R.id.framOrders);
        CustomMenu = LayoutInflater.from(orders.this).inflate(R.layout.custom_menu,null);
        CustomMenu.setVisibility(View.INVISIBLE);
        framOrders.addView(CustomMenu);
        orders_btn=CustomMenu.findViewById(R.id.orders_btn);
        basket_btn=CustomMenu.findViewById(R.id.basket_btn);
        account_btn=CustomMenu.findViewById(R.id.account_btn);
        logout_btn=CustomMenu.findViewById(R.id.logout_btn);
        //add listener to buttons
        account_btn.setOnClickListener(this);
        orders_btn.setOnClickListener(this);
        basket_btn.setOnClickListener(this);
        logout_btn.setOnClickListener(this);
        menuBtn_order.setOnClickListener(this);
        backbtn_order.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "this action blocked  use button in upper page", Toast.LENGTH_SHORT).show();
    }

    public void showMenu(){
        popup= AnimationUtils.loadAnimation(this,R.anim.popup_anim);
        popup.setDuration(500);
        CustomMenu.setAnimation(popup);
        CustomMenu.setVisibility(View.VISIBLE);
        popup.start();
    }
    public void hideMenu(){
        popdown=AnimationUtils.loadAnimation(this,R.anim.popdown);
        popdown.setDuration(500);
        CustomMenu.startAnimation(popdown);
        popdown.start();
        popdown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                CustomMenu.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onClick(View view) {
       //to save any data if delete any thing of order list
        {
            SharedPreferences.Editor save = getorderslist.edit();
            ordersList = gson.toJson(Orders);
            save.putString("orderlist", ordersList).commit();
        }
        if (CustomMenu.getVisibility()==View.VISIBLE){
            hideMenu();
        }
        if (view.getId()==R.id.backbtn_order){
            if (CustomMenu.getVisibility()==View.VISIBLE){
                hideMenu();
            }

            finish();
        } else if (view.getId()==R.id.menuBtn_order){
            if (CustomMenu.getVisibility()==View.INVISIBLE){
                showMenu();
            }
        }else if (view.getId()==R.id.account_btn) {
            startActivity(new Intent(this, account.class));

        } else if (view.getId()==R.id.logout_btn) {
            startActivity(new Intent(this, login.class));
        }else if (view.getId()==R.id.orders_btn){

            Toast.makeText(this, "you are already here", Toast.LENGTH_SHORT).show();

        }else if (view.getId()==R.id.basket_btn){
            startActivity(new Intent(this, baskets.class));
        }
    }
}