package com.ahmad.thelemoapp.adabter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.thelemoapp.R;
import com.ahmad.thelemoapp.ThanksForOrders;
import com.ahmad.thelemoapp.custmList.OrdersList;

import java.util.ArrayList;
import java.util.List;

public class adabterorder extends RecyclerView.Adapter<adabterorder.orderHolder> {
   Context context;
   ArrayList <OrdersList> list;

    public adabterorder(Context context, ArrayList<OrdersList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public orderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.box_items_orders,parent,false);

        return new orderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull orderHolder holder, int position) {
holder.name_fruitTv.setText("#ID\t\t" +list.get(position).getOrderid());
holder.totalprice_order.setText(String.valueOf(list.get(position).getTotal_price()+ "\t JOD"));
holder.deletbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        list.remove(holder.getAdapterPosition());
        notifyItemRemoved(holder.getAdapterPosition());
        notifyDataSetChanged();


    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class orderHolder extends RecyclerView.ViewHolder {
TextView name_fruitTv,totalprice_order;
Button deletbtn;
        public orderHolder(@NonNull View itemView) {
            super(itemView);
            name_fruitTv=itemView.findViewById(R.id.name_fruitTv);
            deletbtn=itemView.findViewById(R.id.deletebtn);
            totalprice_order=itemView.findViewById(R.id.totalprice_order);
        }
    }
}
