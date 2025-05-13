package com.polikarpov.catalog.api;

import com.polikarpov.catalog.model.CatalogResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/catalog") // Заменить api для получения json
    Call<CatalogResponse> getCatalog();
}
