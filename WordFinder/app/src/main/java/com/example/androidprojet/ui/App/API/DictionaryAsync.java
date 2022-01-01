package com.example.androidprojet.ui.App.API;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DictionaryAsync extends AsyncTask<String, Void, List<DictionaryAPI>> {

    final String parameter;
    private DefChangeListener mListener;


    public DictionaryAsync(String parameter, DefChangeListener mListener ) { // constructeur vide car on a pas de paramètre ici
        this.parameter = parameter;
        this.mListener= mListener;
    }

    @Override
    protected List<DictionaryAPI> doInBackground(String... strings) {
        if(this.parameter != null) { // on prend en paramètre le mot à rechercher
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en/") // on met le lien de l'api
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Dictionary service = retrofit.create(Dictionary.class);
            try { // on met le try pour éviter que l'application ne plante en cas de problème
                Response<List<DictionaryAPI>> response = service.getAPI(this.parameter).execute(); // on appelle l'api

                List<DictionaryAPI> repositories = response.body();
                if (repositories != null) { // si on a bien reçu quelque chose on le renvoi
                    return repositories;
                }
            } catch (IOException error) { // on peut exécuter du code en cas de problème ici
            }
        }
        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(List<DictionaryAPI> liste) {
        if(liste!=null && mListener != null){ // si on a bien reçu quelque chose

            mListener.OnDefRetrieved(liste);
        }
        super.onPostExecute(liste);
    }

}
