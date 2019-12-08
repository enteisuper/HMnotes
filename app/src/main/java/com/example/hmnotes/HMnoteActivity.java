package com.example.hmnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    RecyclerView titleList;
    Toolbar weatherBar;
    HMnoteAdapter noteAdapter;
    RecyclerView recyclerView;
    HMnoteStorage noteStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaring layout components
        topBar = findViewById(R.id.toolbar);
        //setSupportActionBar(topBar);
        titleList = findViewById(R.id.titleList);
        TextView emptyText = findViewById(R.id.emptyText);
        emptyText.setVisibility(View.GONE);
        noteStorage = new HMnoteStorage(this);
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
        noteAdapter = new HMnoteAdapter(this, noteList);
        recyclerView.setAdapter(noteAdapter);
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
        if (menuItem.getItemId() == R.id.addNote) {
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
