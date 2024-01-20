package com.ahmad.thelemoapp.adabter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.thelemoapp.R;
import com.ahmad.thelemoapp.custmList.basketList;
import com.ahmad.thelemoapp.custmList.customListitems;

import java.util.ArrayList;
import java.util.List;

public class adapterRecycleView extends RecyclerView.Adapter<adapterRecycleView.recyclefruit> {
Context context;
    ArrayList<customListitems> listFruit=new ArrayList<>();
ArrayList <Boolean> increase;
ArrayList <basketList> basket;
ArrayList <Integer>positionlist;

    public adapterRecycleView(Context context, ArrayList<basketList> basket,ArrayList <Integer>positionlist) {
        this.context = context;
        this.basket = basket;
        this.positionlist = positionlist;

    }

    public void addFruit(customListitems addfruit){
        listFruit.add(addfruit);
    }
    @NonNull
    @Override
    public recyclefruit onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.items_fruits_recycleview,parent,false);
        return new recyclefruit(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclefruit holder, @SuppressLint("RecyclerView") int position) {
if (positionlist.size()>0&&positionlist.contains(position)){
listFruit.get(position).setPrice_total(basket.get(positionlist.indexOf(position)).getNumitem()*basket.get(positionlist.indexOf(position)).getPrice());
}
        holder.imgfruit.setImageResource(listFruit.get(position).getImgitems());
holder.name_fruitTv.setText(listFruit.get(position).getname_fruit()+"\t \t \t"+String.valueOf(listFruit.get(position).getPrice() + " JD"));
holder.pricetotal.setText(String.valueOf(listFruit.get(position).getPrice_total()+"\t JOD"));
holder.AddandRemove.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkid) {

        if (radioGroup.getCheckedRadioButtonId()==R.id.addBtn){
if (listFruit.get(position).getPrice_total()==0){
    positionlist.add(position);
    listFruit.get(position).setPrice_total(listFruit.get(position).getPrice());
    basket.add(new basketList(listFruit.get(position).getImgitems(),listFruit.get(position).getname_fruit(),listFruit.get(position).getPrice_total()/listFruit.get(position).getPrice(),listFruit.get(position).getPrice()));
    holder.pricetotal.setText(String.valueOf(listFruit.get(position).getPrice_total()+"JOD"));
    holder.AddandRemove.clearCheck();
}else {
listFruit.get(position).setPrice_total(listFruit.get(position).getPrice_total()+listFruit.get(position).getPrice());
basket.set(positionlist.indexOf(position),new basketList(listFruit.get(position).getImgitems(),listFruit.get(position).getname_fruit(),listFruit.get(position).getPrice_total()/listFruit.get(position).getPrice(),listFruit.get(position).getPrice()));
holder.pricetotal.setText(String.valueOf(listFruit.get(position).getPrice_total()+"\t JOD"));
    holder.AddandRemove.clearCheck();


}
        } else if (checkid==R.id.removeBtn){
            if (listFruit.get(position).getPrice_total()==0){
                Toast.makeText(context, "add one at least", Toast.LENGTH_SHORT).show();
                holder.AddandRemove.clearCheck();
            }else {
                listFruit.get(position).setPrice_total(listFruit.get(position).getPrice_total()-listFruit.get(position).getPrice());
    basket.set(positionlist.indexOf(position),new basketList(listFruit.get(position).getImgitems(),listFruit.get(position).getname_fruit(),listFruit.get(position).getPrice_total()/listFruit.get(position).getPrice(),listFruit.get(position).getPrice()));
if (listFruit.get(position).getPrice_total()==0){
    basket.remove(positionlist.indexOf(position));
    positionlist.remove(positionlist.indexOf(position));
}

                holder.pricetotal.setText(String.valueOf(listFruit.get(position).getPrice_total()+"JOD"));
                holder.AddandRemove.clearCheck();

            }
        }
    }
});
    }

    @Override
    public int getItemCount() {
        return listFruit.size();
    }

    public class recyclefruit extends RecyclerView.ViewHolder{
        TextView pricetotal,name_fruitTv;
        ImageView imgfruit;
        RadioGroup AddandRemove;
         recyclefruit (View itemview){
             super(itemview);
            pricetotal=itemview.findViewById(R.id.pricetotal);
             name_fruitTv=itemview.findViewById(R.id.name_fruitTv);
            imgfruit=itemview.findViewById(R.id.imgFruit);
             AddandRemove=itemview.findViewById(R.id.AddAndRemove);
        }
    }
}
