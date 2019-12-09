package com.example.hmnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.*;

import java.util.Calendar;

//Creates a new Note to display
public class CreateNewNoteActivity extends AppCompatActivity {

    Toolbar topBar;
    EditText noteBody;
    EditText noteSubject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_note);

        //setting display for topbar
        topBar = findViewById(R.id.topBar);
        //setSupportActionBar(topBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Write a note");

        noteBody = findViewById(R.id.noteBody);
        noteSubject = findViewById(R.id.noteSubject);

        changingSubjectChangeListener();
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
    //https://developer.android.com/guide/topics/ui/menus#java
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater newMenuInflator = getMenuInflater();
        newMenuInflator.inflate(R.menu.save_note_menu, menu);
        return true;
    }

    //https://developer.android.com/reference/android/app/Activity.html#onOptionsItemSelected
    //(android.view.MenuItem)
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        //getting date and time
        String date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + ", " +
                Calendar.getInstance().get(Calendar.MONTH) + ", " +
                Calendar.getInstance().get(Calendar.YEAR);

        String time = Calendar.getInstance().get(Calendar.HOUR) + " : " +
                Calendar.getInstance().get(Calendar.MINUTE) + ". ";


        //checking if save button is clicked
        if (menuItem.getItemId() == R.id.addNote) {
            System.out.println("SAVING ======== ");
            //making sure there is something to add
            if (noteSubject.getText().length() > 0) {
                HMnoteObject newNote = new HMnoteObject(noteSubject.getText().toString(),
                        noteBody.getText().toString(), date, time);
                HMnoteStorage noteStorage = new HMnoteStorage(this);
                long noteid = noteStorage.writeNote(newNote);
                HMnoteObject object = noteStorage.getNoteByID(noteid);
                System.out.println("SAVE NOTEID ======== " + noteid);
                //hit back button
                onBackPressed();

                if (!object.getSubject().equals(newNote.getSubject())) {
                    Toast.makeText(this, "Your Note Failed To Save",
                            Toast.LENGTH_LONG).show();
                }   else {
                    Toast.makeText(this, "Your Note Has Been Saved",
                            Toast.LENGTH_LONG).show();
                }
            }   else {
                noteSubject.setError("Please type in a subject");
            }
        }

        if (menuItem.getItemId() == R.id.deleteNote) {
            Toast.makeText(this, "Note Deleted", Toast.LENGTH_LONG).show();
            onBackPressed();
        }

        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
