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

import com.ahmad.thelemoapp.adabter.adapterRecycleView;
import com.ahmad.thelemoapp.custmList.basketList;
import com.ahmad.thelemoapp.custmList.customListitems;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class summer_fruit extends AppCompatActivity implements View.OnClickListener{
RecyclerView summerfruitRecycle;
adapterRecycleView adapterRecycleSummer;
    private Button menubtn_summerfruit,backbtn_summerfruit,account_btn,orders_btn,logout_btn,basket_btn;
    private  View custommenu;
    private FrameLayout Frame_summerlayout;
    private   Animation popup,popdown;

    private ArrayList<basketList>  basketList=new ArrayList<>();
private  ArrayList <Integer>position_index=new ArrayList<>();
    private SharedPreferences listSaver;
    Gson summer=new Gson();
    String summerbasketindex,basketsummer;//for data  gson
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summer_fruit);
        //initialize shared preference to save  data and send to  basket also check   if their old data  or not
        listSaver=getSharedPreferences("arrays",MODE_PRIVATE);
        summerbasketindex=listSaver.getString("summerbasketindex",null);
        basketsummer=listSaver.getString("summer",null);
        if (summerbasketindex!=null){
            Type type=new TypeToken<ArrayList<Integer>>(){}.getType();
            position_index=summer.fromJson(summerbasketindex,type);
        }else {
            position_index=new ArrayList<>();
        }
        if (basketsummer!=null){
            Type type=new TypeToken<ArrayList<basketList>>(){}.getType();
            basketList=summer.fromJson(basketsummer,type);
        }else {
            basketList=new ArrayList<>();
        }
        //  initialize Recycle view and adapter
      summerfruitRecycle=findViewById(R.id.summerfruitRecycle);
      adapterRecycleSummer =new adapterRecycleView(summer_fruit.this,basketList,position_index);
        adapterRecycleSummer.addFruit(new customListitems(R.drawable.strawberry,"strawberry",5,0));
        adapterRecycleSummer.addFruit(new customListitems(R.drawable.cherry,"cherry",2,0));
        adapterRecycleSummer.addFruit(new customListitems(R.drawable.apricot,"apricot",9,0));
        adapterRecycleSummer.addFruit(new customListitems(R.drawable.mango,"mango",2,0));
        adapterRecycleSummer.addFruit(new customListitems(R.drawable.kiwi,"kiwi",5,0));
        adapterRecycleSummer.addFruit(new customListitems(R.drawable.watermelon,"watermelon",3,0));
        adapterRecycleSummer.addFruit(new customListitems(R.drawable.pineapple,"pineapple",5,0));
        adapterRecycleSummer.addFruit(new customListitems(R.drawable.banana,"banana",4,0));
        adapterRecycleSummer.addFruit(new customListitems(R.drawable.guava,"guava",5,0));
        adapterRecycleSummer.addFruit(new customListitems(R.drawable.muskmelon,"muskmelon",4,0));
        adapterRecycleSummer.addFruit(new customListitems(R.drawable.apple,"apple",9,0));
        summerfruitRecycle.setLayoutManager(new GridLayoutManager(summer_fruit.this,2));
        summerfruitRecycle.setAdapter(adapterRecycleSummer);
        // initialize button ,frame and menu
menubtn_summerfruit=findViewById(R.id.menubtn_summerfruit);
backbtn_summerfruit=findViewById(R.id.backbtn_summerfruit);
custommenu= LayoutInflater.from(summer_fruit.this).inflate(R.layout.custom_menu,null);
        basket_btn=custommenu.findViewById(R.id.basket_btn);
        orders_btn=custommenu.findViewById(R.id.orders_btn);
        account_btn=custommenu.findViewById(R.id.account_btn);
        logout_btn=custommenu.findViewById(R.id.logout_btn);
Frame_summerlayout=findViewById(R.id.frame_summerlayout);
custommenu.setVisibility(View.INVISIBLE);
Frame_summerlayout.addView(custommenu);
//add listener to all button
menubtn_summerfruit.setOnClickListener(this);
basket_btn.setOnClickListener(this);
account_btn.setOnClickListener(this);
orders_btn.setOnClickListener(this);
logout_btn.setOnClickListener(this);
backbtn_summerfruit.setOnClickListener(this);
//shared preference to save data and send to  basket
 listSaver=getSharedPreferences("arrays",MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        SharedPreferences.Editor listSave=listSaver.edit();
        if (basketList.size()>0){
            basketsummer  =summer.toJson(basketList);
            summerbasketindex=summer.toJson(position_index);
            listSave.putString("summer",basketsummer).commit();
            listSave.putString("summerbasketindex",summerbasketindex).commit();
        }
if (view.getId()==R.id.menubtn_summerfruit){
   if (custommenu.getVisibility()==View.VISIBLE){
       hideMenu();
   }else {
       showMenu();
   }

}
 else if (view.getId()==R.id.backbtn_summerfruit) {
finish();

}
else if (view.getId()==R.id.orders_btn) {
    startActivity(new Intent(this,orders.class));

} else if (view.getId()==R.id.account_btn) {
    startActivity(new Intent(this,account.class));

}
   else if (view.getId()==R.id.basket_btn){
   startActivity(new Intent(this,baskets.class));

}
  else if (view.getId()==R.id.logout_btn) {
    startActivity(new Intent(this,login.class));
}
 }

    private void hideMenu() {
        popdown=AnimationUtils.loadAnimation(this,R.anim.popdown);
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
        });
    }

    private void showMenu(){
        popup= AnimationUtils.loadAnimation(this,R.anim.popup_anim);
        custommenu.startAnimation(popup);
        popup.setDuration(1000);
        popup.start();
        custommenu.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        SharedPreferences.Editor listSave=listSaver.edit();
        if (basketList.size()>0){
            basketsummer  =summer.toJson(basketList);
            summerbasketindex=summer.toJson(position_index);
            listSave.putString("summer",basketsummer).commit();
            listSave.putString("summerbasketindex",summerbasketindex).commit();
        }      custommenu.setVisibility(View.INVISIBLE);
    finish();

    }

}