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

import java.util.List;

public class LocalDataSource implements DataSource {

    private static NoteDao mNoteDao;
    private static GitHubUserDao mGitHubUserDao;

    public LocalDataSource(NoteDao noteDao, GitHubUserDao gitHubUserDao) {
        this.mNoteDao = noteDao;
        this.mGitHubUserDao = gitHubUserDao;
    }


    @Override
    public void insertNote(Note note) {
        new insertNoteAsyncTask().execute(note);
    }

    @Override
    public void updateNote(Note note) {
        new updateNoteAsyncTask().execute(note);
    }

    @Override
    public void deleteNote(Note note) {
        new deleteNoteAsyncTask().execute(note);
    }

    @Override
    public void deleteAllNotes() {
        new deleteAllNotesAsyncTask().execute();
    }

    @Override
    public LiveData<List<Note>> getAllNotes() {
        return mNoteDao.getAllNotes();
    }


    private static class insertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private static final String ROOM_ERROR = "ROOM ERROR";

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
        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.update(notes[0]);
            return null;
        }
    }

    private static class deleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            mNoteDao.delete(notes[0]);
            return null;
        }
    }

    private static class deleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            mNoteDao.deleteAllNotes();
            return null;
        }
    }


    @Override
    public void insertGitHubUser(GitHubUser gitHubUser) {
        new insertGitHubUserAsyncTask().execute(gitHubUser);
    }

    @Override
    public void updateGitHubUser(GitHubUser gitHubUser) {
        new updateGitHubUserAsyncTask().execute(gitHubUser);
    }

    @Override
    public void deleteGitHubUser(GitHubUser gitHubUser) {
        new deleteGitHubUserAsyncTask().execute(gitHubUser);
    }

    @Override
    public void deleteAllGitHubUsers() {
        new deleteAllGitHubUsersAsyncTask().execute();
    }

    @Override
    public List<GitHubUser> getAllGitHubUsers() {
        return mGitHubUserDao.getAllGitHubUsers();
    }

    private static class insertGitHubUserAsyncTask extends AsyncTask<GitHubUser, Void, Void> {
        private static final String ROOM_ERROR = "ROOM ERROR";

        @Override
        protected Void doInBackground(GitHubUser... gitHubUsers) {
            try {
                mGitHubUserDao.insertGitHubUser(gitHubUsers[0]);
            } catch (SQLiteConstraintException e) {
                Log.e(ROOM_ERROR, e.getMessage());
            }
            return null;
        }
    }

    private static class updateGitHubUserAsyncTask extends AsyncTask<GitHubUser, Void, Void> {
        @Override
        protected Void doInBackground(GitHubUser... gitHubUsers) {
            mGitHubUserDao.updateGitHubUser(gitHubUsers[0]);
            return null;
        }
    }

    private static class deleteGitHubUserAsyncTask extends AsyncTask<GitHubUser, Void, Void> {
        @Override
        protected Void doInBackground(GitHubUser... gitHubUsers) {
            mGitHubUserDao.deleteGitHubUser(gitHubUsers[0]);
            return null;
        }
    }

    private static class deleteAllGitHubUsersAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            mGitHubUserDao.deleteAllGitHubUsers();
            return null;
        }
    }


    @Override
    public void getGitHubUsersRetrofit(MutableLiveData<List<GitHubUser>> liveDataGitHubUsers, String since) {

    }

    @Override
    public void getGitHubUsersRxJava(MutableLiveData<List<GitHubUser>> liveDataGitHubUsers, String since) {

    }
}
