package edu.depaul.assignment2_tushar_kumar;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteEntryViewHolder> {

    private final List<Note> noteList;
    private MainActivity mainAct;

    NoteAdapter(List<Note> list, MainActivity ma){
        this.noteList = list;
        this.mainAct = ma;
    }

    @NonNull
    @Override
    public NoteEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteEntryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
