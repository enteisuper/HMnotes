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

public class HMnoteAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private List<HMnoteObject> notes;

    HMnoteAdapter(Context setContext, List<HMnoteObject> setNotes) {
        inflater = LayoutInflater.from(setContext);
        notes = setNotes;
    }

    //creating viewholder class to hold and create the front page of titles and dates
    public class ViewTitleDates extends RecyclerView.ViewHolder {
        TextView textSubject;
        TextView textDate;

        public ViewTitleDates(@NonNull final View view) {
            super(view);
            textSubject = view.findViewById(R.id.textSubject);
            textDate = view.findViewById(R.id.textDate);
            //
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), HMnoteModify.class);
                    intent.putExtra("ID", notes.get(getAdapterPosition()).getId());
                    view.getContext().startActivity(intent);
                }
            });
        }
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
        //recast so parameters can be used
        ViewTitleDates titleDates = (ViewTitleDates) holder;

        String subject = notes.get(position).getSubject();
        String createDate= notes.get(position).getCreateDate();
        long noteid = notes.get(position).getId();

        titleDates.textDate.setText(createDate);
        titleDates.textSubject.setText(subject);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
