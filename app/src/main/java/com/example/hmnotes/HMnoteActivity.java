package com.example.hmnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import java.util.List;

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
        TextView emptyText = findViewById(R.id.emptyText);
        emptyText.setVisibility(View.GONE);
        HMnoteStorage noteStorage = new HMnoteStorage(this);
        List<HMnoteObject> noteList = noteStorage.retrieveAllNotes();

        if (noteList.size() == 0) {
            emptyText.setVisibility(View.VISIBLE);
        }   else {
            emptyText.setVisibility(View.GONE);


        }


        weatherBar = findViewById(R.id.weatherBar);
    }

    public void showList(List<HMnoteObject> noteList) {
        titleList.setLayoutManager(new LinearLayoutManager(this));

    }


}
