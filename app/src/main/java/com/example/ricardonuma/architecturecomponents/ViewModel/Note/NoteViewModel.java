package com.example.ricardonuma.architecturecomponents.ViewModel.Note;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ricardonuma.architecturecomponents.data.Repository;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import java.util.List;

public class NoteViewModel extends ViewModel {

    private Repository mRepository;
    private LiveData<List<Note>> mLiveDataAllNotes;

    public NoteViewModel(Repository repository) {
        mRepository = repository;
        getAllNotes();
    }

    public void insertNote(Note note) {
        mRepository.insertNote(note);
    }

    public void updateNote(Note note) {
        mRepository.updateNote(note);
    }

    public void deleteNote(Note note) {
        mRepository.deleteNote(note);
    }

    public void deleteAllNotes() {
        mRepository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mLiveDataAllNotes = mRepository.getAllNotes();
    }
}
