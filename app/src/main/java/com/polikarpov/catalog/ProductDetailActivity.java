package com.polikarpov.catalog;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.polikarpov.catalog.model.CatalogItem;

import java.util.Locale;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        String productJson = getIntent().getStringExtra("product");
        CatalogItem product = new Gson().fromJson(productJson, CatalogItem.class);

        ImageView imageView = findViewById(R.id.product_image);
        TextView titleTextView = findViewById(R.id.product_title);
        TextView priceTextView = findViewById(R.id.product_price);
        TextView descriptionTextView = findViewById(R.id.product_description);

        titleTextView.setText(product.getName());
        priceTextView.setText(String.format(Locale.getDefault(), "%.2f руб.", product.getPrice()));
        descriptionTextView.setText(product.getDescription());

        Glide.with(this)
                .load(product.getImage())
                .placeholder(R.drawable.placeholder)
                .into(imageView);
    }
}
