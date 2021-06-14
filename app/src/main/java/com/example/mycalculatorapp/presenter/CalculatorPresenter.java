package com.example.mycalculatorapp.presenter;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.mycalculatorapp.model.ActionsEnum;
import com.example.mycalculatorapp.model.CalculatorModel;
import com.example.mycalculatorapp.view.CalculatorActivity;

public class CalculatorPresenter implements Parcelable {

    private CalculatorActivity calculatorActivity;
    private CalculatorModel calculatorModel;

    private double prevNumber;
    private double currentNumber;
    private boolean isDotted = false;
    private ActionsEnum currentAction;

    public CalculatorPresenter(CalculatorActivity calculatorActivity) {
        this.calculatorActivity = calculatorActivity;
        this.calculatorModel = new CalculatorModel();
        prevNumber = 0;
        currentNumber = 0;
        currentAction = null;
    }

    protected CalculatorPresenter(Parcel in) {
        prevNumber = in.readDouble();
        currentNumber = in.readDouble();
        isDotted = in.readByte() != 0;
    }

    public static final Creator<CalculatorPresenter> CREATOR = new Creator<CalculatorPresenter>() {
        @Override
        public CalculatorPresenter createFromParcel(Parcel in) {
            return new CalculatorPresenter(in);
        }

        @Override
        public CalculatorPresenter[] newArray(int size) {
            return new CalculatorPresenter[size];
        }
    };

    public void onDigitKeyPressed(String digit) {
        String numberString = numberFormatter(currentNumber);
        numberString += digit;
        currentNumber = Double.parseDouble(numberString);
        calculatorActivity.setTextToMon(numberFormatter(currentNumber));
    }

    private String numberFormatter(double someNumber) {
        long numberInt = (long) someNumber;
        double eps = 0.00000001;
        if (Math.abs(someNumber - numberInt) > eps) {
            return Double.toString(someNumber);
        } else {
            if (isDotted) {
                return Long.toString(numberInt) + ".";
            } else {
                return Long.toString(numberInt);
            }
        }
    }

    public void onDotKeyPressed() {
        if (isDotted) {} else {
            isDotted = true;
            numberFormatter(currentNumber);
            calculatorActivity.setTextToMon(numberFormatter(currentNumber));
        }
    }

    public void onBinaryOperatorPressed(ActionsEnum action) {
        if (currentAction != null) {
            try {
                currentNumber = calculatorModel.equalAction(prevNumber,currentNumber,currentAction);
            } catch (Exception e) {
                onDelete();
            }
        }
        currentAction = action;
        prevNumber = currentNumber;
        calculatorActivity.setTextToMon(numberFormatter(currentNumber));
        currentNumber = 0;
        isDotted = false;
    }

    public void onEqualKeyPressed() {
        try {
            currentNumber = calculatorModel.equalAction(prevNumber,currentNumber,currentAction);
        } catch (Exception e) {
            onDelete();
        }
        currentAction = null;
        prevNumber = currentNumber;
        calculatorActivity.setTextToMon(numberFormatter(currentNumber));
        currentNumber = 0;
        isDotted = false;
    }

    public void onSqrtPressed() {
        try {
            currentNumber = calculatorModel.doSqrt(currentNumber);
        } catch (Exception e) {
            onDelete();
        }
        calculatorActivity.setTextToMon(numberFormatter(currentNumber));
    }

    public void onSignPressed() {
        currentNumber = calculatorModel.changeSign(currentNumber);
        calculatorActivity.setTextToMon(numberFormatter(currentNumber));
    }

    public void onDelete() {
        currentNumber = 0;
        prevNumber = 0;
        currentAction = null;
        isDotted = false;
        calculatorActivity.setTextToMon(numberFormatter(currentNumber));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(prevNumber);
        dest.writeDouble(currentNumber);
        dest.writeByte((byte) (isDotted ? 1 : 0));
    }

}
