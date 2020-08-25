package com.example.ricardonuma.architecturecomponents.data.source.Local;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ricardonuma.architecturecomponents.data.DataSource;
import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUserDao;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.NoteDao;

import java.util.ArrayList;
import java.util.List;

import rx.Single;

public class LocalDataSource implements DataSource {

    private NoteDao mNoteDao;
    private GitHubUserDao mGitHubUserDao;
    private static MutableLiveData<List<Note>> mAllNotes = new MutableLiveData<>();

    public LocalDataSource(NoteDao noteDao, GitHubUserDao gitHubUserDao) {
        this.mNoteDao = noteDao;
        this.mGitHubUserDao = gitHubUserDao;
    }


    @Override
    public void insertNote(Note note) {
        new insertNoteAsyncTask(mNoteDao).execute(note);
    }

    @Override
    public void updateNote(Note note) {
        new updateNoteAsyncTask(mNoteDao).execute(note);
    }

    @Override
    public void deleteNote(Note note) {
        new deleteNoteAsyncTask(mNoteDao).execute(note);
    }

    @Override
    public void deleteAllNotes() {
        new deleteAllNotesAsyncTask(mNoteDao).execute();
    }

    @Override
    public MutableLiveData<List<Note>> getAllNotes() {
        new getAllNotesAsyncTask(mNoteDao).execute();
        return mAllNotes;
    }


    private static class insertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private static final String ROOM_ERROR = "ROOM ERROR";
        private NoteDao mNoteDao;

        private insertNoteAsyncTask(NoteDao noteDao) {
            this.mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            try {
                mNoteDao.insert(notes[0]);
            } catch (SQLiteConstraintException e) {
                Log.e(ROOM_ERROR, e.getMessage());
            }
            return null;
        }
    }

    private static class updateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao mNoteDao;

        private updateNoteAsyncTask(NoteDao noteDao) {
            this.mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.update(notes[0]);
            return null;
        }
    }

    private static class deleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao mNoteDao;

        private deleteNoteAsyncTask(NoteDao noteDao) {
            this.mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.delete(notes[0]);
            return null;
        }
    }

    private static class deleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao mNoteDao;

        private deleteAllNotesAsyncTask(NoteDao noteDao) {
            this.mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mNoteDao.deleteAllNotes();
            return null;
        }
    }

    private static class getAllNotesAsyncTask extends AsyncTask<Void, Void, List<Note>> {
        private NoteDao mNoteDao;

        private getAllNotesAsyncTask(NoteDao noteDao) {
            this.mNoteDao = noteDao;
        }

        @Override
        protected List<Note> doInBackground(Void... voids) {
            return mNoteDao.getAllNotes();
        }

        @Override
        protected void onPostExecute(List<Note> notes) {
            super.onPostExecute(notes);
            mAllNotes.setValue(notes);
        }
    }


    @Override
    public void insertGitHubUser(GitHubUser gitHubUser) {
        mGitHubUserDao.insert(gitHubUser);
    }

    @Override
    public void updateGitHubUser(GitHubUser gitHubUser) {
        mGitHubUserDao.update(gitHubUser);
    }

    @Override
    public void deleteGitHubUser(GitHubUser gitHubUser) {
        mGitHubUserDao.delete(gitHubUser);
    }

    @Override
    public void deleteAllGitHubUsers() {
        mGitHubUserDao.deleteAllGitHubUsers();
    }

    @Override
    public List<GitHubUser> getAllGitHubUsers() {
        return mGitHubUserDao.getAllGitHubUsers();
    }


    @Override
    public LiveData<List<GitHubUser>> usersCall(String since) {
        return null;
    }

    @Override
    public Single<List<GitHubUser>> usersObservable(String since) {
        return null;
    }
}
