package com.example.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.myapp.Adapter.CardListAdapter;
import com.example.myapp.Helper.ManagmentCard;
import com.example.myapp.Interface.ChangeNumberItemsListeners;
import com.example.myapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CardListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagmentCard managmentCard;
    private TextView totalFeeTxt, taxTxt, delivaryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        managmentCard = new ManagmentCard(this);

        initView();
        initList();
        calculateCard();
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton=findViewById(R.id.card_btn);
        LinearLayout homeBtn=findViewById(R.id.home_btn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardListActivity.this,CardListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardListActivity.this, MainActivity.class));
            }
        });
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new CardListAdapter(managmentCard.getListCard(), this, new ChangeNumberItemsListeners() {
            @Override
            public void changed() {
                calculateCard();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if(managmentCard.getListCard().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void calculateCard(){
        double percentTax=0.05;
        double delivery=12;

        tax = Math.round((managmentCard.getTotalFee()*percentTax)*100.0)/100.0;
        double total = Math.round((managmentCard.getTotalFee()+tax+delivery)*100.0)/100.0;
        double itemTotal = Math.round(managmentCard.getTotalFee()*100.0)/100.0;

        totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$"+tax);
        delivaryTxt.setText("$"+delivery);
        totalTxt.setText("$"+total);

    }
    private void initView() {
        recyclerViewList = findViewById(R.id.recycleview);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        delivaryTxt=findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        emptyTxt=findViewById(R.id.emptyTxt);
        scrollView=findViewById(R.id.scrollView4);
    }
}