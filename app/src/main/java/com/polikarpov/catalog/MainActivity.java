package com.polikarpov.catalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.polikarpov.catalog.adapter.CatalogAdapter;
import com.polikarpov.catalog.api.ApiClient;
import com.polikarpov.catalog.api.ApiService;
import com.polikarpov.catalog.model.CatalogItem;
import com.polikarpov.catalog.model.CatalogResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadCatalogData(); // После настройки url к серверу, закомментируйте строчку и раскомментируйте следующую
//        loadCatalogFromServer();
    }

    private void loadCatalogFromServer() {
        ApiService apiService = ApiClient.getApiService();
        Call<CatalogResponse> call = apiService.getCatalog();

        call.enqueue(new Callback<CatalogResponse>() {
            @Override
            public void onResponse(Call<CatalogResponse> call, Response<CatalogResponse> response) {
                if (response.isSuccessful()) {
                    CatalogResponse catalogResponse = response.body();
                    CatalogAdapter adapter = new CatalogAdapter(catalogResponse.getCatalog(), item -> {
                        Intent intent;
                        if (item.getItems() != null && !item.getItems().isEmpty()) {
                            intent = new Intent(MainActivity.this, SubcategoryActivity.class);
                            intent.putExtra("items", new Gson().toJson(item.getItems()));
                            intent.putExtra("title", item.getName());
                        } else {
                            intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                            intent.putExtra("product", new Gson().toJson(item));
                        }
                        startActivity(intent);
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CatalogResponse> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Error of internet: " + throwable.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCatalogData() {
        progressBar.setVisibility(View.VISIBLE);

        String jsonString = loadJSONFromAsset();
        if (jsonString != null) {
            try {
                Gson gson = new Gson();
                CatalogResponse response = gson.fromJson(jsonString, CatalogResponse.class);

                CatalogAdapter adapter = new CatalogAdapter(response.getCatalog(), item -> {
                    Intent intent;
                    if (item.getItems() != null && !item.getItems().isEmpty()) {
                        intent = new Intent(MainActivity.this, SubcategoryActivity.class);
                        intent.putExtra("items", new Gson().toJson(item.getItems()));
                        intent.putExtra("title", item.getName());
                    } else {
                        intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                        intent.putExtra("product", new Gson().toJson(item));
                    }
                    startActivity(intent);
                });

                recyclerView.setAdapter(adapter);
            } catch (Exception exception) {
                exception.printStackTrace();
                Toast.makeText(this, "Ошибка загрузки каталога", Toast.LENGTH_SHORT).show();
            }
        }
        progressBar.setVisibility(View.GONE);
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("catalog.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}