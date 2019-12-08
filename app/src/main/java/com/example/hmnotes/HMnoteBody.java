package com.example.hmnotes;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HMnoteBody extends AppCompatActivity {
    long noteid;

    @Override
    protected void onCreate(Bundle saveState) {
        super.onCreate(saveState);
        setContentView(R.layout.note_body);
        Toolbar topBar =  findViewById(R.id.topBar);
        //delete button
        FloatingActionButton floatingDeleteButton = findViewById(R.id.floatingDeleteButton);
        setSupportActionBar(topBar);

        //picking up intent extras from adapter
        Intent intent = getIntent();
        noteid = intent.getLongExtra("noteid", 0);
        HMnoteStorage noteStorage = new HMnoteStorage(this);
        HMnoteObject object = noteStorage.getNoteByID(noteid);
        getSupportActionBar().setTitle(object.getSubject());

        TextView noteBody = findViewById(R.id.noteBody);
        noteBody.setText(object.getDetail());
        //details can be scrolled through if it's too long
        noteBody.setMovementMethod(new ScrollingMovementMethod());

        floatingDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HMnoteStorage noteStorage = new HMnoteStorage(getApplicationContext());
                //removing the current note
                noteStorage.removeNote(noteid);
                Toast.makeText(HMnoteBody.this, "Note has been Deleted",
                        Toast.LENGTH_LONG).show();
                toListView();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_note_menu, menu);
        return true;
    }

    @Override
    //if edit note button is pressed
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.editNote) {
            Intent intent = new Intent(this, CreateNewNoteActivity.class);
            intent.putExtra("noteid", noteid);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //returns user to top view of the app
    private void toListView() {
        Intent intent = new Intent(this, HMnoteActivity.class);
        startActivity(intent);
    }
}
