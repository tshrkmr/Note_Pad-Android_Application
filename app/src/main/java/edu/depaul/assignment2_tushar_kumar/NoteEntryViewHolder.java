package edu.depaul.assignment2_tushar_kumar;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteEntryViewHolder extends RecyclerView.ViewHolder {

    TextView noteTitle;
    TextView date;
    TextView note;

    public NoteEntryViewHolder(@NonNull View itemView) {
        super(itemView);

        noteTitle = itemView.findViewById(R.id.noteTitleTextView);
        note = itemView.findViewById(R.id.noteTextView);
        date = itemView.findViewById(R.id.recyclerView);
    }
}
