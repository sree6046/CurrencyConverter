package com.srinivas.currencyconverter;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiService
{

    @GET("fc78a64c5b57cb9c46d6cb34/latest/USD")
    Call<ResponseBody> getPopularBlog();

}
