package com.ahmad.thelemoapp.adabter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.thelemoapp.R;
import com.ahmad.thelemoapp.custmList.listviewpagerMain;
import com.ahmad.thelemoapp.green_fruit;
import com.ahmad.thelemoapp.summer_fruit;
import com.ahmad.thelemoapp.winter_fruit;

import java.util.ArrayList;

public class adapterViewpagerMain extends RecyclerView.Adapter<adapterViewpagerMain.viewpagermain> {
    ArrayList<listviewpagerMain> listviewpagerMain=new ArrayList<>();
    Context context;

    public adapterViewpagerMain(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public viewpagermain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_main,parent,false);
view.setFitsSystemWindows(true);

        return new viewpagermain(view);
    }
    public void additems(listviewpagerMain viewpager){
        listviewpagerMain.add(viewpager);
    }

    @Override
    public void onBindViewHolder(@NonNull viewpagermain holder, int position) {
        holder.logo_type_fruit.setImageResource(listviewpagerMain.get(position).getImg_res());
       holder.text_type_fruit.setText(listviewpagerMain.get(position).getTypefruit());
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (listviewpagerMain.get(holder.getAdapterPosition()).getTypefruit().equals("SUMMER \n FRUIT")){
            context.startActivity(new Intent(context, summer_fruit.class));
        } else if (listviewpagerMain.get(holder.getAdapterPosition()).getTypefruit().equals("Winter \n FRUIT")) {
            context.startActivity(new Intent(context, winter_fruit.class));

        } else if (listviewpagerMain.get(holder.getAdapterPosition()).getTypefruit().equals("Green \n FRUIT")) {
            context.startActivity(new Intent(context, green_fruit.class));

        }

    }
});
    }

    @Override
    public int getItemCount() {
        return listviewpagerMain.size();
    }

    public class viewpagermain extends RecyclerView.ViewHolder {
        ImageView logo_type_fruit;
        TextView text_type_fruit;
        public viewpagermain(View itemview){
            super(itemview);

           logo_type_fruit =itemview.findViewById(R.id.logo_type_fruit);
            text_type_fruit =itemview.findViewById(R.id.text_type_fruit);

        }
    }
}
