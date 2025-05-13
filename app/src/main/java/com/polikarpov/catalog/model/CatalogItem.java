package com.polikarpov.catalog.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogItem {

    private int id;
    private String name;
    private String image;
    private String description;
    private List<CatalogItem> items;
    private double price;

}
