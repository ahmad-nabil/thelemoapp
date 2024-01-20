package com.ahmad.thelemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
Animation popup,popdown;
View CustomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        }
    }
}