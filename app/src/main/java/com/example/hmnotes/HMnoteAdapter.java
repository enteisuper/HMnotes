package com.example.hmnotes;

import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HMnoteAdapter extends RecyclerView.Adapter<ViewNoteTitleHolder> {

    private LayoutInflater inflater;
    private List<HMnoteObject> notes;

    HMnoteAdapter(Context setContext, List<HMnoteObject> setNotes) {
        inflater = LayoutInflater.from(setContext);
        notes = setNotes;
    }

    @NonNull
    @Override
    public ViewNoteTitleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View addingView = inflater.inflate(R.layout.note_view_list, parent, false);
        return new ViewNoteTitleHolder(addingView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewNoteTitleHolder holder, int position) {
        //recast so parameters can be used
        //ViewNoteTitleHolder titleDates = (ViewNoteTitleHolder) holder;

        String subject = notes.get(position).getSubject();
        String createDate= notes.get(position).getCreateDate();
        long noteid = notes.get(position).getId();


        holder.textDate.setText(createDate);
        holder.textSubject.setText(subject);
        holder.noteid = noteid;
        System.out.println("display noteid ===== " + noteid + "    : SUBJECT ====== " + subject);
    }


    @Override
    public int getItemCount() {
        System.out.println("GETITEMCOUNT ===== " + notes.size());
        return notes.size();
    }
}



