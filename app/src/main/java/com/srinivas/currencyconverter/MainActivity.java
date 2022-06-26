package com.srinivas.currencyconverter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1,spinner2;
    private MainViewModel mainViewModel;
    private LinearLayout linearLayout;
    private TextView calculateBtn;
    private EditText editText;
    private int flag=0;
    private double currencyValue;
    private String fromCurrency,toCurrency;
    private Typeface proximaLight, proximaRegular, proximaSemiBold, proximaBold;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        proximaLight = Typeface.createFromAsset(getAssets(), "fonts/Proxima_Nova_Thin.ttf");
        proximaRegular = Typeface.createFromAsset(getAssets(), "fonts/Proxima_Nova_Alt_Regular.ttf");
        proximaSemiBold = Typeface.createFromAsset(getAssets(), "fonts/Proxima_Nova_Semibold.ttf");
        proximaBold = Typeface.createFromAsset(getAssets(), "fonts/Proxima_Nova_Alt_Bold.ttf");


        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        linearLayout = findViewById(R.id.linearone);
        calculateBtn = findViewById(R.id.calculateBtn);
        editText = findViewById(R.id.editText);

        editText.setTypeface(proximaSemiBold);
        calculateBtn.setTypeface(proximaSemiBold);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        getSpinnerData();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText.setText("");
                if(flag == 0)
                getSpinnerSwap();
                else
                getSpinnerData();

            }
        });


        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String editValue = editText.getText().toString();

                if(!editValue.equals("") && Integer.parseInt(editValue) !=0)
                {
                int editTextConvertValue = Integer.parseInt(editValue);
                double subValue;
                double calculate;

                if (flag == 0) {

                    if (currencyValue < 1) {
                        subValue = 1 - currencyValue;
                        double multiplyWithQuantiry = subValue * editTextConvertValue;
                        double finalValue = multiplyWithQuantiry + editTextConvertValue;
                        calculate = Math.round(finalValue * 100.0) / 100.0;
                    } else {
                        subValue = 1 / currencyValue;
                        double multiplyWithQuantiry = subValue * editTextConvertValue;
                        calculate = Math.round(multiplyWithQuantiry * 100.0) / 100.0;
                    }
                } else {
                    double multiplyWithQuantiry = currencyValue * editTextConvertValue;
                    calculate = Math.round(multiplyWithQuantiry * 100.0) / 100.0;
                }

                Intent i = new Intent(MainActivity.this, ConvertActivity.class);
                i.putExtra("value1", editText.getText().toString());
                i.putExtra("value2", calculate + "");
                i.putExtra("fromCurrency", fromCurrency);
                i.putExtra("toCurrency", toCurrency);
                i.putExtra("currencyValue",currencyValue+"");
                startActivity(i);
            }
                else
                {
                    Toast.makeText(MainActivity.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private void getSpinnerData()
    {
        flag = 0;
        mainViewModel.getSpinnerData().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable ArrayList<String> arrayList) {


                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_spinner_dropdown_item, arrayList);
                adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter);


                ArrayList<String> arrayList2 = new ArrayList<>();
                arrayList2.add(arrayList.get(0));
                ArrayAdapter<String> adapter2 =
                        new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_spinner_dropdown_item, arrayList2);
                adapter2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter2);
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                editText.setText("");
                Object item = adapterView.getItemAtPosition(position);
                if (item != null)
                {
                    fromCurrency = spinner1.getSelectedItem().toString();
                    toCurrency = spinner2.getSelectedItem().toString();

                    mainViewModel.getAllDataValues().observe(
                    MainActivity.this, new Observer<HashMap<String, String>>() {
                        @Override
                        public void onChanged(@Nullable HashMap<String, String> hashMap)
                        {
                           // editText.setText(hashMap.get(item.toString()));
                            currencyValue = Double.parseDouble(hashMap.get(item.toString()));
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });
    }


    private void getSpinnerSwap()
    {
        flag = 1;
        mainViewModel.getSpinnerData().observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable ArrayList<String> arrayList) {

                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_spinner_dropdown_item, arrayList);
                adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter);


                ArrayList<String> arrayList2 = new ArrayList<>();
                arrayList2.add(arrayList.get(0));
                ArrayAdapter<String> adapter2 =
                        new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_spinner_dropdown_item, arrayList2);
                adapter2.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter2);
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                editText.setText("");
                Object item = adapterView.getItemAtPosition(position);
                if (item != null)
                {
                    fromCurrency = spinner1.getSelectedItem().toString();
                    toCurrency = spinner2.getSelectedItem().toString();

                    mainViewModel.getAllDataValues().observe(
                            MainActivity.this, new Observer<HashMap<String, String>>() {
                                @Override
                                public void onChanged(@Nullable HashMap<String, String> hashMap) {
                                    currencyValue = Double.parseDouble(hashMap.get(item.toString()));
                                }
                            });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub

            }
        });
    }
}
