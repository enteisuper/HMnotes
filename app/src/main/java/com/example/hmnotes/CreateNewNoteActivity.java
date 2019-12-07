package com.example.hmnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.widget.*;

//Creates a new Note to display
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

    //detecting the change in texts
    public void changingSubjectChangeListener() {
        noteSubject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int numOne,
                                          int numTwo, int numThree) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int numOne,
                                      int numTwo, int numThree) {

                if (charSequence != null && charSequence.length() > 0) {
                    getSupportActionBar().setTitle(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

}
