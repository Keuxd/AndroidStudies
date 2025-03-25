package com.example.tests;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

        Button clickButton = findViewById(R.id.register_button);
        clickButton.setOnClickListener(view -> {
            if(areFieldsValid()) {
                Alert.s("Usuário cadastrado com sucesso!");
            }
        });

        instance = this;
    }
    //Returns true if field is valid, false otherwise
    public boolean validateField(LinearLayout field) {
        TextView text = (TextView) field.getChildAt(0);
        EditText input = (EditText) field.getChildAt(1);

        if(!input.getText().toString().matches("^(?!\\s*$).+")) {
            Alert.s("O campo '" + text.getText() +  "' é obrigatório.");
            return false;
        }

        return true;
    }

    public boolean areFieldsValid() {
        LinearLayout fields = findViewById(R.id.fields);

        for(int i = 0; i < fields.getChildCount(); i++) {
            if(!validateField((LinearLayout) fields.getChildAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static Context getAppContext() {
        return instance;
    }

}