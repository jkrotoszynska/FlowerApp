package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapp.Adapter.PopularAdapter;
import com.example.myapplication.Adapter.CategoryAdapter;
import com.example.myapplication.Domain.CategoryDomain;
import com.example.myapplication.Domain.FlowersDomain;
import com.example.myapplication.R;

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
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList=findViewById(R.id.view2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<com.example.myapplication.Domain.FlowersDomain> flowerList = new ArrayList<>();
        flowerList.add(new FlowersDomain("100 roses", "rosess","Tutaj zostanie zamieszczony opis tych kwiatów. Pamiętać o języku angielskim!",56.00));
        flowerList.add(new FlowersDomain("Colors of sky", "sky", "Tutaj zostanie zamieszczony opis tych kwiatów. Pamiętać o języku angielskim!", 28.00));
        flowerList.add(new FlowersDomain("Spring is coming", "spring", "Tutaj zostanie zamieszczony opis tych kwiatów. Pamiętać o języku angielskim!", 22.00));
        flowerList.add(new FlowersDomain("It's rainbow", "rainbow", "Tutaj zostanie zamieszczony opis tych kwiatów. Pamiętać o języku angielskim!", 25.00 ));

        adapter2 = new PopularAdapter(flowerList);
        recyclerViewPopularList.setAdapter(adapter2);

    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList=findViewById(R.id.view1);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<com.example.myapplication.Domain.CategoryDomain> categoryList = new ArrayList<>();
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