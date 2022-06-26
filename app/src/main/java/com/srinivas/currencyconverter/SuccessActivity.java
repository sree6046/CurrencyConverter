package com.srinivas.currencyconverter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class SuccessActivity extends AppCompatActivity {

    private TextView messageTv;
    private String toCurrency,toValue,currencyValue;
    private Typeface proximaLight, proximaRegular, proximaSemiBold, proximaBold;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        proximaLight = Typeface.createFromAsset(getAssets(), "fonts/Proxima_Nova_Thin.ttf");
        proximaRegular = Typeface.createFromAsset(getAssets(), "fonts/Proxima_Nova_Alt_Regular.ttf");
        proximaSemiBold = Typeface.createFromAsset(getAssets(), "fonts/Proxima_Nova_Semibold.ttf");
        proximaBold = Typeface.createFromAsset(getAssets(), "fonts/Proxima_Nova_Alt_Bold.ttf");

        messageTv = findViewById(R.id.messageTv);
        messageTv.setTypeface(proximaSemiBold);

        Intent i = getIntent();
        toValue = i.getStringExtra("toValue");
        toCurrency = i.getStringExtra("toCurrency");
        currencyValue = i.getStringExtra("currencyValue");

        String str;
        if(toCurrency.equals("USD"))
        {
            str = currencyValue+" / 1";
        }
        else
        {
            str = "1/ "+currencyValue;

        }
        messageTv.setText("Great now you have "+toValue+" "+toCurrency+ " in your account.\n\n Your conversion rate was: \n "+str);


    }

}
