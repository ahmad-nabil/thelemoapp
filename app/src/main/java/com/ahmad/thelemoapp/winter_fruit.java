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

public class winter_fruit extends AppCompatActivity implements View.OnClickListener {

    RecyclerView winterfruitRecycle;
    adapterRecycleView adapterRecycleWinter;
    private Button backbtn_winterfruit,menubtn_winterfruit,account_btn,orders_btn,logout_btn,basket_btn;
    private View CustomMenu;
    private FrameLayout Frame_winterlayot;
    private Animation popup,popdown;
    private ArrayList<basketList> basketList=new ArrayList<>();
    private  ArrayList <Integer>position_index=new ArrayList<>();

    private SharedPreferences listSaver;
    Gson winter=new Gson();
    String winterbasketindex,basketwinter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winter_fruit);
        //initialize shared preference to save  data and send to  basket also check   if their old data  or not
        listSaver=getSharedPreferences("arrays",MODE_PRIVATE);
        winterbasketindex=listSaver.getString("winterbasketindex",null);
        basketwinter=listSaver.getString("basketwinter",null);
        if (winterbasketindex!=null){
            Type type=new TypeToken<ArrayList<Integer>>(){}.getType();
            position_index= winter.fromJson(winterbasketindex,type);
        }else {
            position_index=new ArrayList<>();
        }
        if (basketwinter!=null){
            Type type=new TypeToken<ArrayList<basketList>>(){}.getType();
            basketList= winter.fromJson(basketwinter,type);
        }else {
            basketList=new ArrayList<>();
        }
        //  initialize Recycle view and adapter
adapterRecycleWinter=new adapterRecycleView(winter_fruit.this,basketList,position_index);
        adapterRecycleWinter.addFruit(new customListitems(R.drawable.persimmon,"persimmon",5,0));
        adapterRecycleWinter.addFruit(new customListitems(R.drawable.pomegranate,"pomegranate",2,0));
        adapterRecycleWinter.addFruit(new customListitems(R.drawable.kiwi,"kiwi",2,0));
        adapterRecycleWinter.addFruit(new customListitems(R.drawable.pineapple,"pineapple",5,0));
        adapterRecycleWinter.addFruit(new customListitems(R.drawable.banana,"banana",4,0));
        adapterRecycleWinter.addFruit(new customListitems(R.drawable.guava,"guava",5,0));
        adapterRecycleWinter.addFruit(new customListitems(R.drawable.orange,"orange",4,0));
        adapterRecycleWinter.addFruit(new customListitems(R.drawable.imgsplash,"lemonade",6,0));
        adapterRecycleWinter.addFruit(new customListitems(R.drawable.clementine,"clementine",9,0));
        adapterRecycleWinter.addFruit(new customListitems(R.drawable.grapefruit,"grapefruit",1,0));
        adapterRecycleWinter.addFruit(new customListitems(R.drawable.apple,"apple",5,0));
    winterfruitRecycle=findViewById(R.id.winterfruitRecycle);
winterfruitRecycle.setLayoutManager(new GridLayoutManager(winter_fruit.this,2));
winterfruitRecycle.setAdapter(adapterRecycleWinter);
        // initialize button ,frame and menu
        menubtn_winterfruit=findViewById(R.id.menubtn_winterfruit);
        backbtn_winterfruit=findViewById(R.id.backbtn_winterfruit);
        CustomMenu = LayoutInflater.from(winter_fruit.this).inflate(R.layout.custom_menu,null);
        basket_btn= CustomMenu.findViewById(R.id.basket_btn);
        orders_btn= CustomMenu.findViewById(R.id.orders_btn);
        account_btn= CustomMenu.findViewById(R.id.account_btn);
        logout_btn= CustomMenu.findViewById(R.id.logout_btn);
        Frame_winterlayot=findViewById(R.id.Frame_winterlayot);
        CustomMenu.setVisibility(View.INVISIBLE);
        Frame_winterlayot.addView(CustomMenu);
//add listener to all button
        menubtn_winterfruit.setOnClickListener(this);
        basket_btn.setOnClickListener(this);
        account_btn.setOnClickListener(this);
        orders_btn.setOnClickListener(this);
        logout_btn.setOnClickListener(this);
        backbtn_winterfruit.setOnClickListener(this);
        //shared preference to save  data and send to  basket
        listSaver=getSharedPreferences("arrays",MODE_PRIVATE);
    }
    @Override
    public void onClick(View view) {
        if (basketList.size()>0){
            SharedPreferences.Editor listSave=listSaver.edit();
            basketwinter=winter.toJson(basketList);
            winterbasketindex=winter.toJson(position_index);
            listSave.putString("basketwinter",basketwinter).commit();
            listSave.putString("winterbasketindex",winterbasketindex).commit();
        }
        if (CustomMenu.getVisibility()==View.VISIBLE){
            hideMenu();
        }
        if (view.getId()==R.id.menubtn_winterfruit){
            if (CustomMenu.getVisibility()==View.VISIBLE){
                hideMenu();
            }else {
                showMenu();
            }

        } else if (view.getId()==R.id.backbtn_winterfruit) {

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
        CustomMenu.startAnimation(popup);
        popup.start();
        CustomMenu.setVisibility(View.VISIBLE);
    }
    private void hideMenu(){
        popdown= AnimationUtils.loadAnimation(this,R.anim.popdown);
        popdown.setDuration(1000);
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
        });    }

    @Override
    public void onBackPressed() {
        if (basketList.size()>0){
            SharedPreferences.Editor listSave=listSaver.edit();
            basketwinter=winter.toJson(basketList);
            winterbasketindex=winter.toJson(position_index);
            listSave.putString("basketwinter",basketwinter).commit();
            listSave.putString("winterbasketindex",winterbasketindex).commit();
        }
        CustomMenu.setVisibility(View.INVISIBLE);
        finish();
    }
}