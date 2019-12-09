package com.example.hmnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.JsonArray;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.List;

//https://developer.android.com/reference/android/support/v7/app/AppCompatActivity
public class HMnoteActivity extends AppCompatActivity {
    Toolbar topBar;
    RecyclerView titeListView;
    public static TextView weatherBar;
    HMnoteAdapter noteAdapter;
    HMnoteStorage noteStorage;

    public static Context mainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainContext = this;

        //declaring layout components
        topBar = findViewById(R.id.topBar);
        //setSupportActionBar(topBar);
        titeListView = findViewById(R.id.titleList);
        TextView emptyText = findViewById(R.id.emptyText);
        emptyText.setVisibility(View.GONE);
        noteStorage = new HMnoteStorage(this);
        List<HMnoteObject> noteList = noteStorage.retrieveAllNotes();

        if (noteList.size() == 0) {
            emptyText.setVisibility(View.VISIBLE);
        }   else {
            emptyText.setVisibility(View.GONE);
            showList(noteList);
        }


        weatherBar = findViewById(R.id.weatherBar);
        displayWeatherBar();
    }




    public void displayWeatherBar() {
        new URLJsonTask().execute(
                "https://samples.openweathermap.org/data/2.5/weather?zip=61801%2Cus&appid=b6907d289e10d714a6e88b30761fae22");

    }

    public void showList(List<HMnoteObject> noteList) {
        titeListView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new HMnoteAdapter(this, noteList);
        titeListView.setAdapter(noteAdapter);
    }

    @Override
    //inflating menu for additional note
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean returnValue = true;
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.top_display_menu,menu);
        return returnValue;
    }

    @Override
    //if adding note is clicked
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.createNew) {
            //showing user that a note is being created
            Toast.makeText(this, "Adding Note", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, CreateNewNoteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    //app is ready to take in user input
    protected void onResume() {
        super.onResume();
        TextView emptyText = findViewById(R.id.emptyText);
        List<HMnoteObject> noteList = noteStorage.retrieveAllNotes();
        if (noteList.size() == 0) {
            //adding note message is visible
            emptyText.setVisibility(View.VISIBLE);
        }   else {
            emptyText.setVisibility(View.GONE);
            //displaying all notes
            showList(noteList);
        }
    }


}
