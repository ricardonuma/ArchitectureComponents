package com.example.ricardonuma.architecturecomponents.View.Note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ricardonuma.architecturecomponents.R;
import com.example.ricardonuma.architecturecomponents.ViewModel.Note.NoteViewModel;
import com.example.ricardonuma.architecturecomponents.ViewModel.Note.NoteViewModelFactory;
import com.example.ricardonuma.architecturecomponents.data.DefaultRepository;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import java.util.List;

public class NoteListFragment extends Fragment {

    private NoteViewModel mNoteViewModel;

    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView = getView().findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

        final NoteAdapter noteAdapter = new NoteAdapter(getContext());
        mRecyclerView.setAdapter(noteAdapter);

        NoteViewModelFactory noteViewModelFactory =
                new NoteViewModelFactory(DefaultRepository.getRepository(requireActivity().getApplication()));
        mNoteViewModel = ViewModelProviders.of(this, noteViewModelFactory).get(NoteViewModel.class);
        mNoteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                noteAdapter.setNotes(notes);
            }
        });

        Button next = getView().findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.end_action);
            }
        });
    }
}
