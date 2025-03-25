package com.example.imc;

import android.util.Log;

public class Imc {
    public static String getFormattedImc(double weight, double height) {
        double imc = weight / Math.pow(height, 2);
        return String.format("%.02f", imc);
    }
}
