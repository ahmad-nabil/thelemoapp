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

import com.ahmad.thelemoapp.adabter.adapterRecycleView;
import com.ahmad.thelemoapp.custmList.basketList;
import com.ahmad.thelemoapp.custmList.customListitems;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class green_fruit extends AppCompatActivity implements View.OnClickListener {
    RecyclerView greenfruitRecycle;
    adapterRecycleView adapterRecycleGreen;
    private Button backbtn_greenfruit,menubtn_greenfruit,account_btn,orders_btn,logout_btn,basket_btn;
    private View custommenu;
    private FrameLayout Frame_greenLayout;
    private Animation popup,popdown;
    private ArrayList<basketList> basketList;
    private  ArrayList <Integer>position_index;
    Gson green=new Gson();
    String greenbasketindex,basketgreen;//for data  gson
    private SharedPreferences listSaver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_fruit);
        //initialize shared preference to save  data and send to  basket also check   if their old data  or not
        listSaver=getSharedPreferences("arrays",MODE_PRIVATE);
greenbasketindex=listSaver.getString("greenbasketindex",null);
basketgreen=listSaver.getString("green",null);
if (greenbasketindex!=null){
    Type type=new TypeToken<ArrayList<Integer>>(){}.getType();
    position_index=green.fromJson(greenbasketindex,type);
}else {
    position_index=new ArrayList<>();
}

if (basketgreen!=null){
    Type type=new TypeToken<ArrayList<basketList>>(){}.getType();
    basketList=green.fromJson(basketgreen,type);
}else {
    basketList=new ArrayList<>();
}
        //  initialize Recycle view and adapter
        greenfruitRecycle=findViewById(R.id.greenfruitRecycle);
        adapterRecycleGreen=new adapterRecycleView(green_fruit.this,basketList,position_index);
        adapterRecycleGreen.addFruit(new customListitems(R.drawable.guava,"guava",2,0));
        adapterRecycleGreen.addFruit(new customListitems(R.drawable.kiwi,"kiwi",5,0));
        adapterRecycleGreen.addFruit(new customListitems(R.drawable.pineapple,"pineapple",5,0));
        adapterRecycleGreen.addFruit(new customListitems(R.drawable.apple,"apple",9,0));
        adapterRecycleGreen.addFruit(new customListitems(R.drawable.imgsplash,"lemonade",6,0));
        adapterRecycleGreen.addFruit(new customListitems(R.drawable.mango,"mango",2,0));
        greenfruitRecycle.setLayoutManager(new GridLayoutManager(green_fruit.this,2));
        greenfruitRecycle.setAdapter(adapterRecycleGreen);
        // initialize button ,frame and menu
        menubtn_greenfruit=findViewById(R.id.menubtn_greenfruit);
        backbtn_greenfruit=findViewById(R.id.backbtn_greenfruit);
        custommenu= LayoutInflater.from(green_fruit.this).inflate(R.layout.custom_menu,null);
        basket_btn=custommenu.findViewById(R.id.basket_btn);
        orders_btn=custommenu.findViewById(R.id.orders_btn);
        account_btn=custommenu.findViewById(R.id.account_btn);
        logout_btn=custommenu.findViewById(R.id.logout_btn);
        Frame_greenLayout=findViewById(R.id.Frame_greenLayout);
        custommenu.setVisibility(View.INVISIBLE);
        Frame_greenLayout.addView(custommenu);
//add listener to all button
        menubtn_greenfruit.setOnClickListener(this);
        basket_btn.setOnClickListener(this);
        account_btn.setOnClickListener(this);
        orders_btn.setOnClickListener(this);
        logout_btn.setOnClickListener(this);
        backbtn_greenfruit.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if (basketList.size()>0){
            SharedPreferences.Editor listSave=listSaver.edit();
            basketgreen  =green.toJson(basketList);
            greenbasketindex=green.toJson(position_index);
            listSave.putString("green",basketgreen).commit();
            listSave.putString("greenbasketindex",greenbasketindex).commit();
        }
        if (custommenu.getVisibility()==View.VISIBLE) {
            hideMenu();
        }
            if (view.getId()==R.id.menubtn_greenfruit){
            if (custommenu.getVisibility()==View.VISIBLE){
                hideMenu();
            }else {
                showMenu();
            }

        } else if (view.getId()==R.id.backbtn_greenfruit) {
  finish();
        } else if (view.getId()==R.id.orders_btn) {
                startActivity(new Intent(this, orders.class));
        }else if (view.getId()==R.id.basket_btn){
            startActivity(new Intent(this, baskets.class));
        } else if (view.getId()==R.id.logout_btn) {
                startActivity(new Intent(this,login.class));
            } else if (view.getId()==R.id.account_btn) {
                startActivity(new Intent(this,account.class));
            }


    }
    private void showMenu(){
        popup= AnimationUtils.loadAnimation(this,R.anim.popup_anim);
        popup.setDuration(1000);
        custommenu.startAnimation(popup);
        popup.start();
        custommenu.setVisibility(View.VISIBLE);
    }
    private void hideMenu(){
        popdown= AnimationUtils.loadAnimation(this,R.anim.popdown);
        popdown.setDuration(1000);
        custommenu.startAnimation(popdown);
        popdown.start();
        popdown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                custommenu.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });    }

    @Override
    public void onBackPressed() {
        if (basketList.size()>0){
            SharedPreferences.Editor listSave=listSaver.edit();
            basketgreen  =green.toJson(basketList);
            greenbasketindex=green.toJson(position_index);
            listSave.putString("green",basketgreen).commit();
            listSave.putString("greenbasketindex",greenbasketindex).commit();
        }
        if (custommenu.getVisibility()==View.VISIBLE) {
            hideMenu();
        }
        finish();
    }
}
