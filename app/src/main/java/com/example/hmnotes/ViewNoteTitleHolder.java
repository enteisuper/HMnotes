package com.example.hmnotes;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//creating viewholder class to hold and create the front page of titles and dates
public class ViewNoteTitleHolder extends RecyclerView.ViewHolder {
    long noteid;
    TextView textSubject;
    TextView textDate;

    public ViewNoteTitleHolder(@NonNull final View view) {
        super(view);
        textSubject = view.findViewById(R.id.textSubject);
        textDate = view.findViewById(R.id.textDate);
        //
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Storing intent for the body to be used later on
                Intent intent = new Intent(view.getContext(), HMnoteModify.class);
                intent.putExtra("noteid", noteid);
                view.getContext().startActivity(intent);
            }
        });
    }
}