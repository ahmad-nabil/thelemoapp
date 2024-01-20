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

public class newfruit extends AppCompatActivity  implements View.OnClickListener{
    RecyclerView newfruitRecycle;
    adapterRecycleView adapterRecycleNew;
    private Button backbtn_newfruit,menubtn_newfruit,account_btn,orders_btn,logout_btn,basket_btn;
    private View custommenu;
    private FrameLayout frame_newfruitlayout;
    private Animation popup,popdown;
    private ArrayList<basketList> basketList=new ArrayList<>();
    private  ArrayList <Integer>position_index=new ArrayList<>();

    private SharedPreferences listSaver;
    Gson new_fruit=new Gson();
    String newfruitbasketindex,basketnewfruit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_fruit);

        //initialize shared preference to save  data and send to  basket also check   if their old data  or not
        listSaver=getSharedPreferences("arrays",MODE_PRIVATE);
        newfruitbasketindex=listSaver.getString("newfruitbasketindex",null);
        basketnewfruit=listSaver.getString("new_fruit",null);
        if (newfruitbasketindex!=null){
            Type type=new TypeToken<ArrayList<Integer>>(){}.getType();
            position_index=new_fruit.fromJson(newfruitbasketindex,type);
        }else {
            position_index=new ArrayList<>();
        }
        if (basketnewfruit!=null){
            Type type=new TypeToken<ArrayList<basketList>>(){}.getType();
            basketList=new_fruit.fromJson(basketnewfruit,type);
        }else {
            basketList=new ArrayList<>();
        }

        //  initialize Recycle view and adapter
        newfruitRecycle=findViewById(R.id.newfruitRecycle);
        adapterRecycleNew=new adapterRecycleView(newfruit.this,basketList,position_index);
        adapterRecycleNew.addFruit(new customListitems(R.drawable.cherry,"cherry",2,0));
        adapterRecycleNew.addFruit(new customListitems(R.drawable.apricot,"apricot",9,0));
        adapterRecycleNew.addFruit(new customListitems(R.drawable.mango,"mango",2,0));
        adapterRecycleNew.addFruit(new customListitems(R.drawable.kiwi,"kiwi",5,0));
        adapterRecycleNew.addFruit(new customListitems(R.drawable.pineapple,"pineapple",5,0));
        adapterRecycleNew.addFruit(new customListitems(R.drawable.apple,"apple",9,0));
        adapterRecycleNew.addFruit(new customListitems(R.drawable.imgsplash,"lemonade",6,0));
        adapterRecycleNew.addFruit(new customListitems(R.drawable.clementine,"clementine",9,0));
        adapterRecycleNew.addFruit(new customListitems(R.drawable.grapefruit,"grapefruit",1,0));
        newfruitRecycle.setLayoutManager(new GridLayoutManager(newfruit.this,2));
        newfruitRecycle.setAdapter(adapterRecycleNew);

        // initialize button ,frame and menu
        menubtn_newfruit=findViewById(R.id.menubtn_newfruit);
        backbtn_newfruit=findViewById(R.id.backbtn_newfruit);
        custommenu= LayoutInflater.from(newfruit.this).inflate(R.layout.custom_menu,null);
        basket_btn=custommenu.findViewById(R.id.basket_btn);
        orders_btn=custommenu.findViewById(R.id.orders_btn);
        account_btn=custommenu.findViewById(R.id.account_btn);
        logout_btn=custommenu.findViewById(R.id.logout_btn);
        frame_newfruitlayout=findViewById(R.id.frame_newfruitlayout);
        custommenu.setVisibility(View.INVISIBLE);
        frame_newfruitlayout.addView(custommenu);
//add listener to all button
        menubtn_newfruit.setOnClickListener(this);
        basket_btn.setOnClickListener(this);
        account_btn.setOnClickListener(this);
        orders_btn.setOnClickListener(this);
        logout_btn.setOnClickListener(this);
        backbtn_newfruit.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (basketList.size()>0){
            SharedPreferences.Editor listSave=listSaver.edit();
            basketnewfruit=new_fruit.toJson(basketList);
            newfruitbasketindex=new_fruit.toJson(position_index);
            listSave.putString("newfruitbasketindex",newfruitbasketindex).commit();
            listSave.putString("new_fruit",basketnewfruit).commit();
        }
        if (custommenu.getVisibility()==View.VISIBLE){
            hideMenu();}

            if (view.getId()==R.id.menubtn_newfruit){
            if (custommenu.getVisibility()==View.INVISIBLE){
                showMenu();
            }else {
                hideMenu();
            }
        } else if (view.getId()==R.id.backbtn_newfruit) {
                finish();
        } else if (view.getId()==R.id.orders_btn) {
            startActivity(new Intent(this, orders.class));
        }else if (view.getId()==R.id.basket_btn){
            startActivity(new Intent(this, baskets.class));
        } else if (view.getId()==R.id.logout_btn) {
                startActivity(new Intent(this,login.class));
            }else if (view.getId()==R.id.account_btn) {
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
            basketnewfruit=new_fruit.toJson(basketList);
            newfruitbasketindex=new_fruit.toJson(position_index);
            listSave.putString("newfruitbasketindex",newfruitbasketindex).commit();
            listSave.putString("new_fruit",basketnewfruit).commit();
        }
    if (custommenu.getVisibility()==View.VISIBLE) {
       hideMenu();
    }
    finish();
    }
}