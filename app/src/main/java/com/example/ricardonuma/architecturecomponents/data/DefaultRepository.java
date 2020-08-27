package com.example.ricardonuma.architecturecomponents.data;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.LocalDataSource;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;
import com.example.ricardonuma.architecturecomponents.data.source.Local.RoomDatabase;
import com.example.ricardonuma.architecturecomponents.data.source.Remote.RemoteDataSource;

import java.util.List;

public class DefaultRepository implements Repository {

    private static DefaultRepository INSTANCE;

    DataSource mLocalDataSource;
    DataSource mRemoteDataSource;

    public DefaultRepository(DataSource localDataSource, DataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static DefaultRepository getRepository(Context context) {
        if (INSTANCE == null) {
            RoomDatabase database = RoomDatabase.getInstance(context);
            INSTANCE = new DefaultRepository(new LocalDataSource(database.noteDao(), database.gitHubUserDao()), new RemoteDataSource());
        }

        return INSTANCE;
    }

    @Override
    public void insertNote(Note note) {
        mLocalDataSource.insertNote(note);
    }

    @Override
    public void updateNote(Note note) {
        mLocalDataSource.updateNote(note);
    }

    @Override
    public void deleteNote(Note note) {
        mLocalDataSource.deleteNote(note);
    }

    @Override
    public void deleteAllNotes() {
        mLocalDataSource.deleteAllNotes();
    }

    @Override
    public LiveData<List<Note>> getAllNotes() {
        return mLocalDataSource.getAllNotes();
    }


    @Override
    public void insertGitHubUser(GitHubUser gitHubUser) {
        mLocalDataSource.insertGitHubUser(gitHubUser);
    }

    @Override
    public void updateGitHubUser(GitHubUser gitHubUser) {
        mLocalDataSource.updateGitHubUser(gitHubUser);
    }

    @Override
    public void deleteGitHubUser(GitHubUser gitHubUser) {
        mLocalDataSource.deleteGitHubUser(gitHubUser);
    }

    @Override
    public void deleteAllGitHubUsers() {
        mLocalDataSource.deleteAllGitHubUsers();
    }

    @Override
    public List<GitHubUser> getAllGitHubUsers() {
        return mLocalDataSource.getAllGitHubUsers();
    }


    @Override
    public void getGitHubUsersRetrofit(MutableLiveData<List<GitHubUser>> liveDataGitHubUsers, String since) {
        mRemoteDataSource.getGitHubUsersRetrofit(liveDataGitHubUsers, since);
    }

    @Override
    public void getGitHubUsersRxJava(MutableLiveData<List<GitHubUser>> liveDataGitHubUsers, String since) {
        mRemoteDataSource.getGitHubUsersRxJava(liveDataGitHubUsers, since);
    }
}
