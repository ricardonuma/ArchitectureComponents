package com.example.ricardonuma.architecturecomponents.ViewModel.Note;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ricardonuma.architecturecomponents.data.DefaultRepository;
import com.example.ricardonuma.architecturecomponents.data.Repository;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import java.util.List;

public class NoteViewModel extends ViewModel {

    private Repository mRepository;
    private static LiveData<List<Note>> mLiveDataAllNotes = new MutableLiveData<>();

    public NoteViewModel(Repository repository) {
        mRepository = repository;
        getAllNotes();
    }

    public void insertNote(Note note) {
//        new insertNoteAsyncTask(mRepository).execute(note);
        mRepository.insertNote(note);
    }

    public void updateNote(Note note) {
//        new updateNoteAsyncTask(mRepository).execute(note);
        mRepository.updateNote(note);
    }

    public void deleteNote(Note note) {
//        new deleteNoteAsyncTask(mRepository).execute(note);
        mRepository.deleteNote(note);
    }

    public void deleteAllNotes() {
//        new deleteAllNotesAsyncTask(mRepository).execute();
        mRepository.deleteAllNotes();
    }

    public MutableLiveData<List<Note>> getAllNotes() {
//        new getAllNotesAsyncTask(mRepository).execute();
        return mRepository.getAllNotes();
    }

//    public LiveData<List<Note>> getLiveDataAllNotes() {
//        return mLiveDataAllNotes;
//    }

    private static class getAllNotesAsyncTask extends AsyncTask<Void, Void, LiveData<List<Note>>> {
        private Repository mRepository;

        private getAllNotesAsyncTask(Repository repository) {
            this.mRepository = repository;
        }

        @Override
        protected LiveData<List<Note>> doInBackground(Void... voids) {
            return mRepository.getAllNotes();
        }

        @Override
        protected void onPostExecute(LiveData<List<Note>> notes) {
            super.onPostExecute(notes);
            mLiveDataAllNotes = notes;
        }
    }

//
//    private static class insertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
//        private static final String ROOM_ERROR = "ROOM ERROR";
//        private Repository mRepository;
//
//        private insertNoteAsyncTask(Repository repository) {
//            this.mRepository = repository;
//        }
//
//        @Override
//        protected Void doInBackground(Note... notes) {
//            try {
//                mRepository.insertNote(notes[0]);
//            } catch (SQLiteConstraintException e) {
//                Log.e(ROOM_ERROR, e.getMessage());
//            }
//            return null;
//        }
//    }
//
//    private static class updateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
//        private Repository mRepository;
//
//        private updateNoteAsyncTask(Repository repository) {
//            this.mRepository = repository;
//        }
//
//        @Override
//        protected Void doInBackground(Note... notes) {
//            mRepository.updateNote(notes[0]);
//            return null;
//        }
//    }
//
//    private static class deleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
//        private Repository mRepository;
//
//        private deleteNoteAsyncTask(Repository repository) {
//            this.mRepository = repository;
//        }
//
//        @Override
//        protected Void doInBackground(Note... notes) {
//            mRepository.deleteNote(notes[0]);
//            return null;
//        }
//    }
//
//    private static class deleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
//        private Repository mRepository;
//
//        private deleteAllNotesAsyncTask(Repository repository) {
//            this.mRepository = repository;
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            mRepository.deleteAllNotes();
//            return null;
//        }
//    }
//
//    private static class getAllNotesAsyncTask extends AsyncTask<Void, Void, List<Note>> {
//        private Repository mRepository;
//
//        private getAllNotesAsyncTask(Repository repository) {
//            this.mRepository = repository;
//        }
//
//        @Override
//        protected List<Note> doInBackground(Void... voids) {
//            return mRepository.getAllNotes();
//        }
//
//        @Override
//        protected void onPostExecute(List<Note> notes) {
//            super.onPostExecute(notes);
//            mLiveDataAllNotes.setValue(notes);
//        }
//    }
}
