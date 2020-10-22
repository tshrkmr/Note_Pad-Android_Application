package edu.depaul.assignment2_tushar_kumar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class NoteAdapter extends RecyclerView.Adapter<NoteEntryViewHolder> {

    private List<Note> noteList;
    private MainActivity mainAct;

    NoteAdapter(List<Note> list, MainActivity ma){
        this.noteList = list;
        mainAct = ma;
    }

    @NonNull
    @Override
    public NoteEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_entry, parent, false);
        itemView.setOnClickListener(mainAct);
        itemView.setOnLongClickListener(mainAct);
        return new NoteEntryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteEntryViewHolder holder, int position) {
        Note n = noteList.get(position);
        holder.noteTitle.setText(n.getTitle());
        holder.note.setText(n.getContent());
        holder.date.setText(n.getDate());

        String content = n.getContent();
        if(content.length()>80){
            content = n.getContent().substring(0, 79)+"...";
        }
        else {
            content = n.getContent();
        }
        holder.note.setText(content);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
