package com.srinivas.currencyconverter;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainViewModel extends AndroidViewModel
{

    CurrencyRepository blogRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        blogRepository = new CurrencyRepository(application);
    }

        public LiveData<ArrayList<String>> getSpinnerData()
        {
            return blogRepository.getMutableLiveData();
        }

    public LiveData<ArrayList<String>> getSpinnerTwoData()
    {
        return blogRepository.getMutableLiveData();
    }


    public LiveData<HashMap<String, String>>  getAllDataValues()
    {
        return blogRepository.getMutableLiveDataValues();
    }
}
