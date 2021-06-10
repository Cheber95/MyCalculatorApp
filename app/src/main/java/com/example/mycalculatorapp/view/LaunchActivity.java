package com.example.mycalculatorapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mycalculatorapp.R;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        findViewById(R.id.buton_select_light).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchActivity.this, CalculatorActivity.class);
                intent.putExtra(CalculatorActivity.IS_DARK, false);
                startActivity(intent);
            }
        });

        findViewById(R.id.buton_select_dark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchActivity.this, CalculatorActivity.class);
                intent.putExtra(CalculatorActivity.IS_DARK, true);
                startActivity(intent);
            }
        });
    }
}