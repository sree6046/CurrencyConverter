package com.srinivas.currencyconverter;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyRepository
{
    MutableLiveData<HashMap<String, String>> mutableLiveDataValues = new MutableLiveData<>();
    MutableLiveData<ArrayList<String>> mutableLiveData = new MutableLiveData<>();


    Application application;

    public CurrencyRepository(Application application)
    {
        this.application= application;
    }

    public MutableLiveData<ArrayList<String>> getMutableLiveData()
    {
        RestApiService restApiService = RetrofitInstance.getApiService();
        Call<ResponseBody> call = restApiService.getPopularBlog();

        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {

                    String responseRecieved = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseRecieved);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("conversion_rates");

                    ArrayList<String> jsonKeys=new ArrayList<String>();
                    ArrayList<String> jsonValues =new ArrayList<String>();
                    HashMap<String,String> map=new HashMap<String,String>();

                    Iterator<String> x = jsonObject1.keys();
                    while (x.hasNext())
                    {
                        String key1 = x.next();
                        String value1 = jsonObject1.optString(key1);
                        jsonKeys.add(key1);
                        jsonValues.add(value1);
                        map.put(key1,value1);
                    }
                    mutableLiveData.setValue(jsonKeys);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Log.e("TAG>>>>>>>>>>>>>>>>", t.toString());
            }
        });

//        call.enqueue(new Callback<ResponseBody>()
//        {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                try {
//
//                    String responseRecieved = response.body().string();
//
//                   // Add Your Logic
//                  //  Log.d("Success>>>>>>>>>>>>>>>>", responseRecieved);
//                    JSONObject jsonObject = new JSONObject(responseRecieved);
//                    JSONObject jsonObject1 = jsonObject.getJSONObject("conversion_rates");
//
//
//                    ArrayList<String> jsonKeys=new ArrayList<String>();
//                    ArrayList<String> jsonValues =new ArrayList<String>();
//                    HashMap<String,String> map=new HashMap<String,String>();
//                    ArrayList<HashMap<String, String>> prodArrayList = new ArrayList<HashMap<String, String>>();
//
//
//                    //List<String> jsonKeys = new ArrayList<>();
//                    //List<String> jsonValues = new ArrayList<>();
//                    Iterator<String> x = jsonObject1.keys();
//                    while (x.hasNext())
//                    {
//                        String key1 = x.next();
//                        String value1 = jsonObject1.optString(key1);
//                        jsonKeys.add(key1);
//                        jsonValues.add(value1);
//                        map.put(key1,value1);
//                    }
//                    prodArrayList.add(map);
//                    Log.d("jsonKeys>>>>>>>>>>>>>>>>", jsonKeys.toString());
//                    Log.d("jsonValues>>>>>>>>>>>>>>>>", jsonValues.toString());
//                    Log.d("prodArrayList>>>>>>>>>>>>>>>>", prodArrayList.toString());
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t)
//            {
//                Log.e("TAG>>>>>>>>>>>>>>>>", t.toString());
//            }
//        });

        return mutableLiveData;
    }


    public MutableLiveData<HashMap<String, String>>  getMutableLiveDataValues()
    {
        RestApiService restApiService = RetrofitInstance.getApiService();
        Call<ResponseBody> call = restApiService.getPopularBlog();


        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {

                    String responseRecieved = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseRecieved);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("conversion_rates");

                    ArrayList<String> jsonKeys=new ArrayList<String>();
                    ArrayList<String> jsonValues =new ArrayList<String>();
                    HashMap<String,String> map=new HashMap<String,String>();
                    HashMap<String, String> prodArrayList = new HashMap<String, String>();

                    Iterator<String> x = jsonObject1.keys();
                    while (x.hasNext())
                    {
                        String key1 = x.next();
                        String value1 = jsonObject1.optString(key1);
                        map.put(key1,value1);
                    }
                    mutableLiveDataValues.setValue(map);
                    Log.d("jsonKeys>>>>>>>>>>>>>>>>", jsonKeys.toString());
                    Log.d("jsonValues>>>>>>>>>>>>>>>>", jsonValues.toString());

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t)
            {
                Log.e("TAG>>>>>>>>>>>>>>>>", t.toString());
            }
        });

        return mutableLiveDataValues;
    }
}
