package com.example.second;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    private static final String[] data = new String[]{
            "X小猪猪", "X小狗狗", "X小鸡鸡", "X小猫猫", "X小咪咪"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView atv_content = findViewById(R.id.atv_content);
        MultiAutoCompleteTextView matv_content = findViewById(R.id.matv_content);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, data);
        atv_content.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, data);
        matv_content.setAdapter(adapter2);
        matv_content.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }



}