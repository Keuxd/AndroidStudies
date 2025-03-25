package com.example.imc;

import android.widget.Toast;

public class Alert {
    public static void s(String text) {
        Toast.makeText(MainActivity.getAppContext(), text, Toast.LENGTH_SHORT).show();
    }
}
