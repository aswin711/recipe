package com.example.cedex.recipe.data.s;

import com.example.cedex.recipe.service.APIService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cedex on 4/11/2017.
 */

public class APIClient {
    private static final String BASE_URL = "http://recipe-api.codebases.com/public/api/v1/";
    private static Retrofit retrofit = null;



    public static  Retrofit getClient(){
        if(retrofit==null){

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(logging);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
