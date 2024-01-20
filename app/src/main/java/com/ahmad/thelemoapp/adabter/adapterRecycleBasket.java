package com.ahmad.thelemoapp.adabter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.thelemoapp.custmList.basketList;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class adapterRecycleBasket extends RecyclerView.Adapter<adapterRecycleBasket.basketHolder> {
    Context context;
    ArrayList<basketList> listbasket;
TextView totalpriceTV;

    public adapterRecycleBasket(Context context, ArrayList<basketList> listbasket, TextView totalpriceTV) {
        this.context = context;
        this.listbasket = listbasket;
        this.totalpriceTV = totalpriceTV;
    }

    @NonNull
    @Override
    public basketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull basketHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class basketHolder extends RecyclerView.ViewHolder {
        public basketHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
