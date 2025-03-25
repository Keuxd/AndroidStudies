package com.example.gasosa;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static AppCompatActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(v -> {

            EditText weightText = findViewById(R.id.weight_input);
            EditText heightText = findViewById(R.id.height_input);

            if(!validateField(weightText) || !validateField(heightText)) {
                return;
            }

            double weight = Double.parseDouble(weightText.getText().toString());
            double height = Double.parseDouble(heightText.getText().toString());

            TextView imcText = findViewById(R.id.imc_text);
            imcText.setText(getString(R.string.imc_text) + " " + getFormmattedWorth(weight, height));
        });

        instance = this;
    }

    public String getFormmattedWorth(double gasoline, double ethanol) {
        if(gasoline * 0.7 <= ethanol) {
            return "Etanol";
        }

        return "Gasolina";
    }

    public boolean validateField(EditText field) {
        if(!field.getText().toString().matches("^(?!\\s*$).+")) {
            Alert.s("Os campos são obrigatórios.");
            return false;
        }

        return true;
    }

    public static Context getAppContext() {
        return instance;
    }
}