package com.example.myapp.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.myapp.Interface.ChangeNumberItemsListeners;

import java.util.ArrayList;

public class ManagmentCard {
    private Context context;
    private TinyDB tinyDB;

    public ManagmentCard(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFLower(com.example.myapp.Domain.FlowersDomain item){
        ArrayList<com.example.myapp.Domain.FlowersDomain> listFlower = getListCard();
        boolean existAlready = false;
        int n = 0;
        for(int i = 0; i < listFlower.size(); i++){
            if(listFlower.get(i).getTitle().equals(item.getTitle())){
                existAlready=true;
                n = i;
                break;
            }
        }
        if(existAlready){
            listFlower.get(n).setNumberInCard(item.getNumberInCard());
        }else{
            listFlower.add(item);
        }
        tinyDB.putListObject("CardList", listFlower);
        Toast.makeText(context, "Added To Yout Card", Toast.LENGTH_SHORT).show();


    }
    public ArrayList<com.example.myapp.Domain.FlowersDomain> getListCard(){
        return tinyDB.getListObject("CardList");
    }

    public void plusNumberFlower(ArrayList<com.example.myapp.Domain.FlowersDomain> listFlower, int position, ChangeNumberItemsListeners changeNumberItemsListeners){
          listFlower.get(position).setNumberInCard(listFlower.get(position).getNumberInCard()+1);
          tinyDB.putListObject("CardList", listFlower);
          changeNumberItemsListeners.changed();
    }

    public void minusNumberFlowers(ArrayList<com.example.myapp.Domain.FlowersDomain> listFlower, int position, ChangeNumberItemsListeners changeNumberItemsListeners) {
       if (listFlower.get(position).getNumberInCard()==1){
           listFlower.remove(position);
       }else{
           listFlower.get(position).setNumberInCard(listFlower.get(position).getNumberInCard() - 1);
       }
       tinyDB.putListObject("CardList", listFlower);
       changeNumberItemsListeners.changed();
    }

    public Double getTotalFee(){
        ArrayList<com.example.myapp.Domain.FlowersDomain> listFlowers2 = getListCard();
        double fee = 0;
        for (int i = 0; i < listFlowers2.size(); i++){
            fee = fee +(listFlowers2.get(i).getFee() * listFlowers2.get(i).getNumberInCard());
        }
        return fee;
    }
}