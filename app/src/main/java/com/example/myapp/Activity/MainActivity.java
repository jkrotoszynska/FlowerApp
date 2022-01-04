package com.example.myapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myapp.Adapter.PopularAdapter;
import com.example.myapp.Adapter.CategoryAdapter;
import com.example.myapp.Domain.CategoryDomain;
import com.example.myapp.Domain.FlowersDomain;
import com.example.myapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();

    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton=findViewById(R.id.card_btn);
        LinearLayout homeBtn=findViewById(R.id.home_btn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CardListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList=findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<com.example.myapp.Domain.FlowersDomain> flowerList = new ArrayList<>();
        flowerList.add(new FlowersDomain("100 roses", "rosess","A huge bouquet of a hundred red roses! Our online florist will take care of the delivery on time. You can attach a free greeting card to the bouquet.",56.00));
        flowerList.add(new FlowersDomain("Colors of sky", "sky", "A beautiful bouquet of flowers in the colors of the sky! Our online florist will take care of the delivery on time. You can attach a free greeting card to the bouquet.", 28.00));
        flowerList.add(new FlowersDomain("Spring is coming", "spring", "A beautiful bouquet that heralds the arrival of spring. Our online florist will take care of the delivery on time. You can attach a free greeting card to the bouquet.", 22.00));
        flowerList.add(new FlowersDomain("It's rainbow", "rainbow", "A charming bouquet in every color, like a rainbow. Our online florist will take care of the delivery on time. You can attach a free greeting card to the bouquet.", 25.00 ));

        adapter2 = new PopularAdapter(flowerList);
        recyclerViewPopularList.setAdapter(adapter2);

    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList=findViewById(R.id.view1);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<com.example.myapp.Domain.CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Lilii", "flow_1"));
        categoryList.add(new CategoryDomain("Gladiolus", "flow_2"));
        categoryList.add(new CategoryDomain("Cloves", "flow_3"));
        categoryList.add(new CategoryDomain("Roses", "flow_4"));
        categoryList.add(new CategoryDomain("Tulips", "flow_5"));
        categoryList.add(new CategoryDomain("Sunflowers", "flow_6"));

        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}