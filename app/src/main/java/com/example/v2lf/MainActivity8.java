package com.example.v2lf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity8 extends AppCompatActivity {
Button button13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        button13 = (Button) findViewById(R.id.button13);



    }
    public void Siguiente(View view)
    {
        Intent siguiente = new Intent(this, MainActivity7.class);
        startActivity(siguiente);
    }

}