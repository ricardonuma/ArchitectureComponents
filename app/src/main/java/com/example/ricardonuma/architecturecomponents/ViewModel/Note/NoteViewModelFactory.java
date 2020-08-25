package com.example.ricardonuma.architecturecomponents.ViewModel.Note;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ricardonuma.architecturecomponents.data.Repository;

public class NoteViewModelFactory implements ViewModelProvider.Factory {

    private final Repository mRepository;

    public NoteViewModelFactory(Repository repository) {
        this.mRepository = repository;
    }

    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NoteViewModel.class)) {
            return (T) new NoteViewModel(mRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

