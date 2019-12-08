package com.example.hmnotes;

import android.content.Context;
import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HMnoteAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private List<HMnoteObject> notes;

    HMnoteAdapter(Context setContext, List<HMnoteObject> setNotes) {
        inflater = LayoutInflater.from(setContext);
        notes = setNotes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View addingView = inflater.inflate(R.layout.list_view, parent, false);
        return new RecyclerView.ViewHolder(addingView) {
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
