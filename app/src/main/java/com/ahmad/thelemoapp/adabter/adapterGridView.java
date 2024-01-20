package com.ahmad.thelemoapp.adabter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.ahmad.thelemoapp.R;
import com.ahmad.thelemoapp.custmList.GridviewList;
import com.ahmad.thelemoapp.green_fruit;
import com.ahmad.thelemoapp.newfruit;
import com.ahmad.thelemoapp.summer_fruit;
import com.ahmad.thelemoapp.winter_fruit;

import java.util.ArrayList;

public class adapterGridView extends BaseAdapter {
    Context context;
ArrayList <GridviewList> Mainmenu=new ArrayList<>();
    public adapterGridView(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Mainmenu.size();
    }
    public void additems(GridviewList list){
        Mainmenu.add(list);

    }
    @Override
    public Object getItem(int position) {
        return Mainmenu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
       View griditemsview;
       if (convertview==null){

           griditemsview= LayoutInflater.from(context).inflate(R.layout.gridview_mainmenu,null);
       }else {
           griditemsview=convertview;
       }
        TextView listmenu=griditemsview.findViewById(R.id.listmenu);
       listmenu.setText(Mainmenu.get(position).getTypeFruit());
        ConstraintLayout contraint_gridview=griditemsview.findViewById(R.id.contraint_gridview);
        contraint_gridview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
switch (position){
    case 0:
        context.startActivity(new Intent(context, summer_fruit.class));
        break;
        case 1:
        context.startActivity(new Intent(context, winter_fruit.class));
        break;
        case 2:
        context.startActivity(new Intent(context, green_fruit.class));
        break;
        case 3:
        context.startActivity(new Intent(context, newfruit.class));
        break;

}
            }
        });
        return griditemsview;
    }
}
