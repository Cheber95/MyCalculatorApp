package com.example.mycalculatorapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mycalculatorapp.R;
import com.example.mycalculatorapp.model.ActionsEnum;
import com.example.mycalculatorapp.presenter.CalculatorPresenter;
import com.google.android.material.button.MaterialButton;

public class CalculatorActivity extends AppCompatActivity {

    private TextView calcMonitor;
    private CalculatorPresenter calculatorPresenter;
    public static final String IS_DARK = "IS_DARK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra(IS_DARK,false)) {
            setTheme(R.style.MyCalculatorAppLight);
        } else {
            setTheme(R.style.MyCalculatorAppDark);
        }
        setContentView(R.layout.activity_main);
        initializationCalc();
    }

    private void initializationCalc() {
        calculatorPresenter = new CalculatorPresenter(this);
        calcMonitor = findViewById(R.id.monitor);

        findViewById(R.id.button_key_0).setOnClickListener(v -> calculatorPresenter.onDigitKeyPressed("0"));

        findViewById(R.id.button_key_1).setOnClickListener(v -> calculatorPresenter.onDigitKeyPressed("1"));

        findViewById(R.id.button_key_2).setOnClickListener(v -> calculatorPresenter.onDigitKeyPressed("2"));

        findViewById(R.id.button_key_3).setOnClickListener(v -> calculatorPresenter.onDigitKeyPressed("3"));

        findViewById(R.id.button_key_4).setOnClickListener(v -> calculatorPresenter.onDigitKeyPressed("4"));

        findViewById(R.id.button_key_5).setOnClickListener(v -> calculatorPresenter.onDigitKeyPressed("5"));

        findViewById(R.id.button_key_6).setOnClickListener(v -> calculatorPresenter.onDigitKeyPressed("6"));

        findViewById(R.id.button_key_7).setOnClickListener(v -> calculatorPresenter.onDigitKeyPressed("7"));

        findViewById(R.id.button_key_8).setOnClickListener(v -> calculatorPresenter.onDigitKeyPressed("8"));

        findViewById(R.id.button_key_9).setOnClickListener(v -> calculatorPresenter.onDigitKeyPressed("9"));

        findViewById(R.id.button_key_equal).setOnClickListener(v -> calculatorPresenter.onEqualKeyPressed());

        findViewById(R.id.button_key_dot).setOnClickListener(v -> calculatorPresenter.onDotKeyPressed());

        findViewById(R.id.button_key_add).setOnClickListener(v -> calculatorPresenter.onBinaryOperatorPressed(ActionsEnum.ADD));

        findViewById(R.id.button_key_sub).setOnClickListener(v -> calculatorPresenter.onBinaryOperatorPressed(ActionsEnum.SUB));

        findViewById(R.id.button_key_mult).setOnClickListener(v -> calculatorPresenter.onBinaryOperatorPressed(ActionsEnum.MULT));

        findViewById(R.id.button_key_div).setOnClickListener(v -> calculatorPresenter.onBinaryOperatorPressed(ActionsEnum.DIV));

        findViewById(R.id.button_key_pow).setOnClickListener(v -> calculatorPresenter.onBinaryOperatorPressed(ActionsEnum.POW));

        findViewById(R.id.button_key_sqrt).setOnClickListener(v -> calculatorPresenter.onSqrtPressed());

        findViewById(R.id.button_key_sign).setOnClickListener(v -> calculatorPresenter.onSignPressed());

        findViewById(R.id.button_key_del).setOnClickListener(v -> calculatorPresenter.onDelete());

    }

    public String getTextMon() {
        return calcMonitor.getText().toString();
    }

    public void setTextToMon(String s) {
        calcMonitor.setText(s);
    }

    private final static String KEY_CALC = "CalculatorState";

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(KEY_CALC, calculatorPresenter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        calculatorPresenter = instanceState.getParcelable(KEY_CALC);
    }

}