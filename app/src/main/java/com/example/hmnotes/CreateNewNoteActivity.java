package com.example.hmnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.widget.*;

public class CreateNewNoteActivity extends AppCompatActivity {

    Toolbar topBar;
    EditText noteBody;
    EditText noteSubject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_note);

        topBar = findViewById(R.id.topBar);
        setSupportActionBar(topBar);

        noteBody = findViewById(R.id.noteBody);
        noteSubject = findViewById(R.id.noteSubject);
    }
}
