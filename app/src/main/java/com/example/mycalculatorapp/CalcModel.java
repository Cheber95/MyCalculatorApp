package com.example.mycalculatorapp;

import android.os.Parcel;
import android.os.Parcelable;

public class CalcModel implements Parcelable {

    private String currentNumber;
    private String previousNumber;
    private ActionsEnum previousAction = null;
    private ActionsEnum currentAction = null;
    private double doublePrev;
    private double doubleCur;


    public CalcModel() {

    }

    protected CalcModel(Parcel in) {
        currentNumber = in.readString();
        previousNumber = in.readString();
    }

    public static final Creator<CalcModel> CREATOR = new Creator<CalcModel>() {
        @Override
        public CalcModel createFromParcel(Parcel in) {
            return new CalcModel(in);
        }

        @Override
        public CalcModel[] newArray(int size) {
            return new CalcModel[size];
        }
    };

    public void clickOperation(String number, ActionsEnum typeOfOperation) {
        currentAction = typeOfOperation; /////
        previousNumber = number;
        currentNumber = "";
    }

    public String clickEqual(String number) {
        currentNumber = number;
        doublePrev = Double.parseDouble(previousNumber);
        doubleCur = Double.parseDouble(currentNumber);
        String res = "";
        try {
            switch (currentAction) {
                case ADD:
                    res = Double.toString(doublePrev + doubleCur);
                    break;
                case SUB:
                    res = Double.toString(doublePrev - doubleCur);
                    break;
                case MULT:
                    res = Double.toString(doublePrev * doubleCur);
                    break;
                case DIV:
                    res = Double.toString(doublePrev / doubleCur);
                    break;
                case POW:
                    res = Double.toString(Math.pow(doublePrev,doubleCur));
                    break;
                case SQRT:
                    res = Double.toString(Math.pow(doublePrev,(1/doubleCur)));
                    break;
                default:
                    delete();
                    return "ERROR. PRESS DEL";
            }
        } catch (Exception e) {
            delete();
            return "ERROR. PRESS DEL";
        }
        return res;
    }

    public boolean isDotted(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                return true;
            }
        }
        return false;
    }

    public String getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(String s) {
        currentNumber = s;
    }

    public void changeSign() {
        double doubleNumber = Double.parseDouble(currentNumber);
        doubleNumber *= -1;
        setCurrentNumber(Double.toString(doubleNumber));
    }

    public void delete() {
        setCurrentNumber("");
        previousNumber = "";
        previousAction = null;
        currentAction = null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(currentNumber);
        dest.writeString(previousNumber);
    }
}
