package com.example.hmnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

public class HMnoteActivity extends AppCompatActivity {

    Toolbar topBar;
    RecyclerView titleList;
    Toolbar weatherBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaring layout components
        topBar = findViewById(R.id.topBar);
        setSupportActionBar(topBar);
        titleList = findViewById(R.id.titleList);
        weatherBar = findViewById(R.id.weatherBar);
    }
}
