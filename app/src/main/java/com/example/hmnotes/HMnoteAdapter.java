package com.example.hmnotes;

import android.content.Context;
import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HMnoteAdapter extends RecyclerView.Adapter<ViewNotesTitleHolder> {

    private LayoutInflater inflater;
    private List<HMnoteObject> notes;

    HMnoteAdapter(Context setContext, List<HMnoteObject> setNotes) {
        inflater = LayoutInflater.from(setContext);
        notes = setNotes;
    }

    @NonNull
    @Override
    public ViewNotesTitleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View addingView = inflater.inflate(R.layout.list_view, parent, false);
        return new ViewNotesTitleHolder(addingView) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull ViewNotesTitleHolder holder, int position) {
        //recast so parameters can be used
        //ViewNotesTitleHolder titleDates = (ViewNotesTitleHolder) holder;

        String subject = notes.get(position).getSubject();
        String createDate= notes.get(position).getCreateDate();
        long noteid = notes.get(position).getId();


        holder.textDate.setText(createDate);
        holder.textSubject.setText(subject);
        holder.noteid = noteid;
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }
}



