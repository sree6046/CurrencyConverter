package com.srinivas.currencyconverter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance
{
    private static Retrofit retrofit = null;
    private static String BASE_URL= "https://v6.exchangerate-api.com/v6/";

    public static RestApiService getApiService()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(RestApiService.class);
    }
}
