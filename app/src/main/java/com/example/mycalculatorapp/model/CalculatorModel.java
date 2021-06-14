package com.example.mycalculatorapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CalculatorModel {

    public CalculatorModel() {
    }

    public double doSqrt(double number) {
        if (number < 0) {
            throw new ArithmeticException("imaginary number");
        } else {
            return Math.sqrt(number);
        }
    }

    public double changeSign(double number) {
        return number *= (-1);
    }

    public double equalAction(double numA, double numB, ActionsEnum action) {
        if (action == ActionsEnum.ADD) {
            return numA + numB;
        } else if (action == ActionsEnum.SUB) {
            return numA - numB;
        } else if (action == ActionsEnum.MULT) {
            return numA * numB;
        } else if (action == ActionsEnum.DIV) {
            if (numB == 0) {
                throw new ArithmeticException("divide by zero");
            } else return numA/numB;
        } else /*if (action == ActionsEnum.POW)*/ {
            return Math.pow(numA,numB);
        }
    }
}
