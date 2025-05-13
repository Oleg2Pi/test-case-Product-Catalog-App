package com.polikarpov.catalog;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.polikarpov.catalog.adapter.CatalogAdapter;
import com.polikarpov.catalog.model.CatalogItem;

import java.util.List;

public class SubcategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String itemsJson = getIntent().getStringExtra("items");
        String title = getIntent().getStringExtra("title");

        setTitle(title);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<CatalogItem> items = new Gson().fromJson(itemsJson,
                new TypeToken<List<CatalogItem>>(){}.getType());

        CatalogAdapter adapter = new CatalogAdapter(items, item -> {
            Intent intent;
            if (item.getItems() != null && !item.getItems().isEmpty()) {
                intent = new Intent(SubcategoryActivity.this, SubcategoryActivity.class);
                intent.putExtra("items", new Gson().toJson(item.getItems()));
                intent.putExtra("title", item.getName());
            } else {
                intent = new Intent(SubcategoryActivity.this, ProductDetailActivity.class);
                intent.putExtra("product", new Gson().toJson(item));
            }
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }
}
