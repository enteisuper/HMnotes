package com.example.hmnotes;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class HMnoteModify extends AppCompatActivity {
    Toolbar topBar;
    EditText textSubject;
    EditText textBody;
    long noteID


    @Override
    protected void onCreate(Bundle saveState) {
        super.onCreate(saveState);
        setContentView(R.layout.note_modify);
        topBar = findViewById(R.id.topBar);
        setSupportActionBar(topBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        noteID = intent.getLongExtra("ID", 0);
        HMnoteStorage noteStorage = new HMnoteStorage(this);
        HMnoteObject object = noteStorage.getNoteByID(noteID);

        final String noteSubject = object.getSubject();
        String noteBody = object.getDetail();
        textSubject = findViewById(R.id.noteSubject);
        textBody = findViewById(R.id.noteBody);
        textSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int numOne,
                                      int numTwo, int numThree) {
                if (charSequence.length() > 0) {
                    getSupportActionBar().setTitle(charSequence);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence,
                                          int numOne, int numTwo, int numThree) {
                getSupportActionBar().setTitle(noteSubject);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        textSubject.setText(noteSubject);
        textBody.setText(noteBody);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.)
    }

}
