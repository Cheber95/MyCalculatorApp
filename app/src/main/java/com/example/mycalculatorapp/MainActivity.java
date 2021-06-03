package com.example.mycalculatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView calcMonitor;
    private Button[] digitKeys = new Button[10];
    private Button[] actionKeys = new Button[6];
    private Button signKey;
    private Button dotKey;
    private Button eqKey;
    private Button delKey;
    private static final int ADD = 0;
    private static final int SUB = 1;
    private static final int MULT = 2;
    private static final int DIV = 3;
    private static final int POW = 4;
    private static final int SQRT = 5;

    private View [] digitListeners = new View[10];
    private View [] actionListeners = new View[8];

    private CalcModel calcModel = new CalcModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializationCalc();
    }

    private void initializationCalc() {
        calcMonitor = findViewById(R.id.monitor);
        digitKeys[0] = findViewById(R.id.button_key_0);
        digitKeys[1] = findViewById(R.id.button_key_1);
        digitKeys[2] = findViewById(R.id.button_key_2);
        digitKeys[3] = findViewById(R.id.button_key_3);
        digitKeys[4] = findViewById(R.id.button_key_4);
        digitKeys[5] = findViewById(R.id.button_key_5);
        digitKeys[6] = findViewById(R.id.button_key_6);
        digitKeys[7] = findViewById(R.id.button_key_7);
        digitKeys[8] = findViewById(R.id.button_key_8);
        digitKeys[9] = findViewById(R.id.button_key_9);

        dotKey = findViewById(R.id.button_key_dot);
        signKey = findViewById(R.id.button_key_sign);
        delKey = findViewById(R.id.button_key_del);
        eqKey = findViewById(R.id.button_key_equal);

        actionKeys[ADD] = findViewById(R.id.button_key_add);
        actionKeys[SUB] = findViewById(R.id.button_key_sub);
        actionKeys[MULT] = findViewById(R.id.button_key_mult);
        actionKeys[DIV] = findViewById(R.id.button_key_div);
        actionKeys[POW] = findViewById(R.id.button_key_sqr);
        actionKeys[SQRT] = findViewById(R.id.button_key_sqrt);

        initKeysClickListener();
    }

    private void initKeysClickListener() {
        for (Button digitKey : digitKeys) {
            digitKey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btn = (Button) v;
                    insertTextToMon(btn.getText().toString());
                    calcModel.setCurrentNumber(getTextMon());
                }
            });
        }
        signKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcModel.changeSign();
                setTextToMon(calcModel.getCurrentNumber());
            }
        });
        dotKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentNumber = getTextMon();
                if (!calcModel.isDotted(currentNumber)) {
                    insertTextToMon(".");
                }
            }
        });
        for (Button actionKey : actionKeys) {
            actionKey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.button_key_add) {
                        calcModel.clickOperation(getTextMon(), ActionsEnum.ADD);
                    } else if (v.getId() == R.id.button_key_sub) {
                        calcModel.clickOperation(getTextMon(), ActionsEnum.SUB);
                    } else if (v.getId() == R.id.button_key_mult) {
                        calcModel.clickOperation(getTextMon(), ActionsEnum.MULT);
                    } else if (v.getId() == R.id.button_key_div) {
                        calcModel.clickOperation(getTextMon(), ActionsEnum.DIV);
                    } else if (v.getId() == R.id.button_key_sqr) {
                        calcModel.clickOperation(getTextMon(), ActionsEnum.POW);
                    } else if (v.getId() == R.id.button_key_sqrt) {
                        calcModel.clickOperation(getTextMon(), ActionsEnum.SQRT);
                    }
                    setTextToMon("");
                }
            });
        }
        delKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextToMon("");
                calcModel.delete();
            }
        });
        eqKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextToMon(calcModel.clickEqual(getTextMon()));
            }
        });
    }

    private void insertTextToMon(String s) {
        String currentText = calcMonitor.getText().toString();
        currentText += s;
        calcMonitor.setText(currentText);
    }

    public String getTextMon() {
        return calcMonitor.getText().toString();
    }

    public void setTextToMon(String s) {
        calcMonitor.setText(s);
    }

    private final static String KeyCalc = "CalculatorState";

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(KeyCalc, calcModel);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        calcModel = instanceState.getParcelable(KeyCalc);
        setTextToMon(calcModel.getCurrentNumber());
    }

}