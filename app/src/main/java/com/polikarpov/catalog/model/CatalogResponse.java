package com.polikarpov.catalog.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogResponse {

    @SerializedName("catalog")
    private List<CatalogItem> catalog;
    @SerializedName("products")
    private List<CatalogItem> products;
}
