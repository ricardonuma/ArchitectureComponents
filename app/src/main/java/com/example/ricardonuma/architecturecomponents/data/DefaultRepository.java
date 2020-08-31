package com.example.ricardonuma.architecturecomponents.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import java.util.List;

public class DefaultRepository implements Repository {

    DataSource mLocalDataSource;
    DataSource mRemoteDataSource;

    public DefaultRepository(DataSource localDataSource, DataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
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
