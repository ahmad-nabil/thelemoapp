package com.ahmad.thelemoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmad.thelemoapp.GenratorIDorder.genrator;
import com.ahmad.thelemoapp.adabter.adapterRecycleBasket;
import com.ahmad.thelemoapp.custmList.OrdersList;
import com.ahmad.thelemoapp.custmList.basketList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class baskets extends AppCompatActivity implements View.OnClickListener {
    ArrayList <basketList>listbasket=new ArrayList<>();
    RecyclerView basketRecycle;
SharedPreferences getlist, getStateCash;
    Gson gson=new Gson();
    private  String basketSummerFruit,basketGreenFruit,basketWinterFruit,basketNewFruit,orderlist;
    adapterRecycleBasket adapterbasket;
    TextView total_priceTV;
    Button backbtn_baskets,menuBtn_baskets,OrderNowBtn,account_btn,orders_btn,logout_btn,basket_btn;
    FrameLayout FrameBasket;
    private View CustomMenu;
    Animation popup,popdown;
    ArrayList <OrdersList> listOrdered;
    EditText address_user;
    genrator genrator;
    RadioButton visaBtn,cashBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baskets);
        //get data Gson stored in shared preference and check if their any data or null
        getlist=getSharedPreferences("arrays",MODE_PRIVATE);
        basketSummerFruit =getlist.getString("summer",null);
        basketGreenFruit =getlist.getString("green",null);
        basketWinterFruit =getlist.getString("basketwinter",null);
        basketNewFruit =getlist.getString("new_fruit",null);
        if(basketSummerFruit==null&&basketGreenFruit==null&&basketWinterFruit==null&&basketNewFruit==null){
            Toast.makeText(this, "You don't have anything", Toast.LENGTH_SHORT).show();
        }else {
            compine_Data(basketSummerFruit,basketGreenFruit,basketWinterFruit,basketNewFruit);
        }
        //initialize Recycle and text view using in adapter
        basketRecycle= findViewById(R.id.basketRecycle);
        total_priceTV=findViewById(R.id.total_priceTV);
        adapterbasket=new adapterRecycleBasket(baskets.this,listbasket, total_priceTV);
basketRecycle.setLayoutManager(new LinearLayoutManager(baskets.this,RecyclerView.VERTICAL,false));
basketRecycle.setAdapter(adapterbasket);
  //initialize Button and menu components
        address_user=findViewById(R.id.address_user);
        menuBtn_baskets=findViewById(R.id.menuBtn_baskets);
        backbtn_baskets=findViewById(R.id.backbtn_baskets);
        OrderNowBtn=findViewById(R.id.OrderNowBtn);
        FrameBasket=findViewById(R.id.Frambasket);
        CustomMenu = LayoutInflater.from(baskets.this).inflate(R.layout.custom_menu,null);
        CustomMenu.setVisibility(View.INVISIBLE);
        FrameBasket.addView(CustomMenu);
        orders_btn=CustomMenu.findViewById(R.id.orders_btn);
        basket_btn=CustomMenu.findViewById(R.id.basket_btn);
        account_btn=CustomMenu.findViewById(R.id.account_btn);
        logout_btn=CustomMenu.findViewById(R.id.logout_btn);
        // set listener for buttons
        account_btn.setOnClickListener(this);
        OrderNowBtn.setOnClickListener(this);
        orders_btn.setOnClickListener(this);
        basket_btn.setOnClickListener(this);
        logout_btn.setOnClickListener(this);
        menuBtn_baskets.setOnClickListener(this);
        backbtn_baskets.setOnClickListener(this);
        //for list order  will check if their any value stored in array List else will intilize as new array and  wait user to order to add them
        orderlist=getlist.getString("orderlist",null);
        if (orderlist!=null){
            Type type=new TypeToken<ArrayList<OrdersList>>(){}.getType();
            listOrdered=gson.fromJson(orderlist,type);
        }else {
            listOrdered=new ArrayList<>();
        }
        genrator=new genrator();//for product id
    //get data for check  what user choose when signup
        getStateCash =getSharedPreferences("user",MODE_PRIVATE);
        visaBtn=findViewById(R.id.visaBtn);
        cashBtn=findViewById(R.id.cashBtn);

if (getStateCash.getBoolean("cash",false)){
    cashBtn.setChecked(true);
}else {
    visaBtn.setChecked(true);

}
    }

    private void compine_Data(String basketSummerFruit, String basketGreenFruit, String basketWinterFruit, String basketNewFruit) {
        if (basketSummerFruit!=null){
            Type type=new TypeToken<ArrayList<basketList>>(){}.getType();
         listbasket.addAll(gson.fromJson(basketSummerFruit,type));
        }
        if(basketGreenFruit!=null) {
            Type type = new TypeToken<ArrayList<basketList>>() {
            }.getType();
            listbasket.addAll(gson.fromJson(basketGreenFruit, type));
        }
        if (basketNewFruit!=null){
            Type type=new TypeToken<ArrayList<basketList>>(){}.getType();
            listbasket.addAll(gson.fromJson(basketNewFruit,type));
        }
        if(basketWinterFruit!=null){
            Type type=new TypeToken<ArrayList<basketList>>(){}.getType();
            listbasket.addAll(gson.fromJson(basketWinterFruit,type));
        }

    }

    @Override
    public void onClick(View view) {
        SharedPreferences.Editor editor=getlist.edit();
        if (view.getId()==R.id.backbtn_baskets){
            if (CustomMenu.getVisibility()==View.VISIBLE){
                hideMenu();
            }
            finish();
        } else if (view.getId()==R.id.menuBtn_baskets){
            if (CustomMenu.getVisibility()==View.VISIBLE){
                hideMenu();
            }else{
                showMenu();
            }
        }else if (view.getId()==R.id.account_btn) {
            startActivity(new Intent(this, account.class));

        } else if (view.getId()==R.id.logout_btn) {
            startActivity(new Intent(this, login.class));
        }else if (view.getId()==R.id.orders_btn){
            startActivity(new Intent(this, orders.class));

        }else if (view.getId()==R.id.basket_btn){
            Toast.makeText(this, "you are already here", Toast.LENGTH_SHORT).show();
        } else if (view.getId()==R.id.OrderNowBtn) {
            if (listbasket.size()>0){

          if (!TextUtils.isEmpty(address_user.getText().toString())){

              int totalprice=0;

              for (int getdata=0;getdata<listbasket.size();){
                  totalprice=totalprice+(listbasket.get(getdata).getNumitem()*listbasket.get(getdata).getPrice());
                  getdata++;
              }
              listOrdered.add(new OrdersList(genrator.getProducts(),totalprice));
              //to save list in gson
              orderlist=gson.toJson(listOrdered);
              editor.putString("orderlist",orderlist);
              //remove all key for list baskets  to start new order without problem
              editor.remove("summer");
              editor.remove("green");
              editor.remove("basketwinter");
              editor.remove("new_fruit");
              editor.remove("summerbasketindex");
              editor.remove("newfruitbasketindex");
              editor.remove("greenbasketindex");
              editor.remove("winterbasketindex");

              editor.commit();
              startActivity(new Intent(this, ThanksForOrders.class));

          }else {
              Toast.makeText(this, "enter your address", Toast.LENGTH_SHORT).show();
          }
            }else {
                Toast.makeText(this, "you dont have any thing in basket", Toast.LENGTH_SHORT).show();
            }

        }

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
}