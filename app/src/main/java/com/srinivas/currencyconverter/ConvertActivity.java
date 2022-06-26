package com.srinivas.currencyconverter;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class ConvertActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private TextView textView1,textView2,convertBtn,timerTv,displayMessage;
    private String fromValue,toValue,fromCurrency,toCurrency,currencyValue;
    int startCountdown = 30;
    int currentCountdown;
    Handler countdownHandler = new Handler();
    Timer countdownTimer = new Timer();
    private Typeface proximaLight, proximaRegular, proximaSemiBold, proximaBold;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        proximaLight = Typeface.createFromAsset(getAssets(), "fonts/Proxima_Nova_Thin.ttf");
        proximaRegular = Typeface.createFromAsset(getAssets(), "fonts/Proxima_Nova_Alt_Regular.ttf");
        proximaSemiBold = Typeface.createFromAsset(getAssets(), "fonts/Proxima_Nova_Semibold.ttf");
        proximaBold = Typeface.createFromAsset(getAssets(), "fonts/Proxima_Nova_Alt_Bold.ttf");


        linearLayout = findViewById(R.id.linearone);
        convertBtn = findViewById(R.id.convertBtn);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        timerTv = findViewById(R.id.timer);
        displayMessage= findViewById(R.id.displayMessage);

        textView1.setTypeface(proximaSemiBold);
        textView2.setTypeface(proximaSemiBold);
        timerTv.setTypeface(proximaSemiBold);
        convertBtn.setTypeface(proximaSemiBold);



        Intent i = getIntent();

        if(i.getStringExtra("value1") !=null &&
                i.getStringExtra("value2") !=null) {

            fromValue = i.getStringExtra("value1");
            toValue = i.getStringExtra("value2");
            fromCurrency = i.getStringExtra("fromCurrency");
            toCurrency = i.getStringExtra("toCurrency");
            currencyValue= i.getStringExtra("currencyValue");
            textView1.setText(fromValue+" "+fromCurrency);
            textView2.setText(toValue+" "+toCurrency);
        }

        startCountdownTimer();

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCustomDialog();
            }
        });
    }

    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        TextView cancelBtn = dialogView.findViewById(R.id.cancelBtn);
        TextView approveBtn = dialogView.findViewById(R.id.approveBtn);
        TextView displayMessage = dialogView.findViewById(R.id.displayMessage);
        displayMessage.setText(getString(R.string.you_are)+ toValue+" "+toCurrency+" for "+fromValue+" "+fromCurrency+"."+ getString(R.string.do_you));

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });

        approveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ConvertActivity.this, SuccessActivity.class);
                i.putExtra("toValue", toValue);
                i.putExtra("toCurrency", toCurrency );
                i.putExtra("currencyValue",currencyValue);
                startActivity(i);
                alertDialog.cancel();
                finish();
            }
        });
    }



    public void startCountdownTimer() {
        currentCountdown = startCountdown;
        for (int i = 0; i <= startCountdown; i++) {
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    countdownHandler.post(doA);
                }
            };
            countdownTimer.schedule(task, i * 1000);
        }
    }
    final Runnable doA = new Runnable() {
        @Override
        public void run() {
            if (currentCountdown != 0) {
                timerTv.setText("" + currentCountdown);
                currentCountdown--;
            } else {
                currentCountdown = startCountdown;
                //startCountdownTimer();
                finish();
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
