package com.example.myapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.Activity.ShowDetailActivity;
import com.example.myapp.Helper.ManagmentCard;
import com.example.myapp.Interface.ChangeNumberItemsListeners;
import com.example.myapp.Domain.FlowersDomain;
import com.example.myapp.R;

import java.util.ArrayList;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolder> {
private    ArrayList<FlowersDomain> FlowersDomains;

    private ManagmentCard managmentCard;
    private ChangeNumberItemsListeners changeNumberItemsListeners;


    public CardListAdapter(ArrayList<FlowersDomain> FlowersDomains, Context context, ChangeNumberItemsListeners changeNumberItemsListeners) {

        this.FlowersDomains = FlowersDomains;
        managmentCard= new ManagmentCard(context);
        this.changeNumberItemsListeners = changeNumberItemsListeners;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_card,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(FlowersDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(FlowersDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((FlowersDomains.get(position).getNumberInCard()*FlowersDomains.get(position).getFee())*100.0)/100.0));
        holder.num.setText(String.valueOf(FlowersDomains.get(position).getNumberInCard()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(FlowersDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                managmentCard.plusNumberFlower(FlowersDomains, position, new ChangeNumberItemsListeners() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListeners.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managmentCard.minusNumberFlowers(FlowersDomains, position, new ChangeNumberItemsListeners() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListeners.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return FlowersDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title2Txt);
            feeEachItem =itemView.findViewById(R.id.feeEachItem);
            pic=itemView.findViewById(R.id.picCard);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            num=itemView.findViewById(R.id.numberItemTxt);
            plusItem=itemView.findViewById(R.id.plus2Btn);
            minusItem=itemView.findViewById(R.id.minus2Btn);
        }
    }
}
