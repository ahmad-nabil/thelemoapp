package com.ahmad.thelemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class ThanksForOrders extends AppCompatActivity implements View.OnClickListener {
    Button backbtnToHome,menubtn,account_btn,orders_btn,logout_btn,basket_btn;
    FrameLayout FrameLayout_thanksfororders;
    private View CustomMenu;
    Animation popup,popdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks_for_orders);
        //initialize Button and menu components
        menubtn=findViewById(R.id.menubtn);
        backbtnToHome=findViewById(R.id.backbtnToHome);
        FrameLayout_thanksfororders=findViewById(R.id.FrameLayout_thanksfororders);
        CustomMenu = LayoutInflater.from(ThanksForOrders.this).inflate(R.layout.custom_menu,null);
        CustomMenu.setVisibility(View.INVISIBLE);
        FrameLayout_thanksfororders.addView(CustomMenu);
        orders_btn=CustomMenu.findViewById(R.id.orders_btn);
        basket_btn=CustomMenu.findViewById(R.id.basket_btn);
        account_btn=CustomMenu.findViewById(R.id.account_btn);
        logout_btn=CustomMenu.findViewById(R.id.logout_btn);
        // set listener for buttons
        account_btn.setOnClickListener(this);
        orders_btn.setOnClickListener(this);
        basket_btn.setOnClickListener(this);
        logout_btn.setOnClickListener(this);
        menubtn.setOnClickListener(this);
        backbtnToHome.setOnClickListener(this);

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
    public void onBackPressed() {
startActivity(new Intent(this,MainActivity.class));    }

    @Override
    public void onClick(View view) {
        if (CustomMenu.getVisibility()==View.VISIBLE){
            hideMenu();
        }
        if (view.getId()==R.id.backbtnToHome){
            if (CustomMenu.getVisibility()==View.VISIBLE){
                hideMenu();
            }
            startActivity(new Intent(this,MainActivity.class));
            finish();
        } else if (view.getId()==R.id.menubtn){
            if (CustomMenu.getVisibility()==View.INVISIBLE){
                showMenu();
            }
        }else if (view.getId()==R.id.account_btn) {
            startActivity(new Intent(this, account.class));
            hideMenu();
        } else if (view.getId()==R.id.logout_btn) {
            startActivity(new Intent(this, login.class));
        }else if (view.getId()==R.id.orders_btn){
            startActivity(new Intent(this, orders.class));

        }else if (view.getId()==R.id.basket_btn){
            startActivity(new Intent(this, baskets.class));

        }
    }
}