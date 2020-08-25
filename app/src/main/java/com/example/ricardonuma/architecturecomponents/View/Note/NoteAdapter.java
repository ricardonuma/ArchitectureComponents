package com.example.ricardonuma.architecturecomponents.View.Note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;
import com.example.ricardonuma.architecturecomponents.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private final LayoutInflater mInflater;
    private List<Note> mNotes = new ArrayList<>();

    public NoteAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new NoteHolder(mInflater.inflate(R.layout.note_item_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int position) {
        Note note = mNotes.get(position);
        noteHolder.title.setText(note.getTitle());
        noteHolder.description.setText(note.getDescription());
        noteHolder.priority.setText(String.valueOf(note.getPriority()));
    }

    public void setNotes(List<Note> noteList) {
        this.mNotes = noteList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mNotes != null) {
            return mNotes.size();
        } else {
            return 0;
        }
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView priority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.text_view_title);
            description = itemView.findViewById(R.id.text_view_description);
            priority = itemView.findViewById(R.id.text_view_priority);
        }
    }
}
