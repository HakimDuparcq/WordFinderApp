package com.example.androidprojet.ui.App.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Dictionary {
    @GET("{mot}") // on met "mot" entre {} pour le fournir en paramètre de la fonction getAPI et rechercher le bon mot
    Call<List<DictionaryAPI>> getAPI(@Path("mot") String mot);
}
