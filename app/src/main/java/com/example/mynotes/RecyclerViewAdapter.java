package com.example.mynotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Note> arrNotes = new ArrayList<>();

    RecyclerViewAdapter(Context context, ArrayList<Note> arrNotes){
        this.context = context;
        this.arrNotes = arrNotes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.note_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.getTitle.setText(arrNotes.get(position).getTitle());
        holder.getContent.setText(arrNotes.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return arrNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView getTitle, getContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            getTitle = itemView.findViewById(R.id.getTitle);
            getContent = itemView.findViewById(R.id.getContent);
        }
    }
}
