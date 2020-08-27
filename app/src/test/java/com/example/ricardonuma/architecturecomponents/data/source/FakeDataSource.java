package com.example.ricardonuma.architecturecomponents.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ricardonuma.architecturecomponents.data.DataSource;
import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import java.util.List;

public class FakeDataSource implements DataSource {

    private MutableLiveData<List<Note>> mNotes;
    private List<GitHubUser> mGitHubUsers;
    private MutableLiveData<List<GitHubUser>> mLiveDataGitHubUsers;

    public FakeDataSource(MutableLiveData<List<Note>> notes, List<GitHubUser> gitHubUsers) {
        this.mNotes = notes;
        this.mGitHubUsers = gitHubUsers;
    }

    public FakeDataSource(MutableLiveData<List<GitHubUser>> gitHubUsers) {
        this.mLiveDataGitHubUsers = gitHubUsers;
    }

    @Override
    public void insertNote(Note note) {
        mNotes.getValue().add(note);
    }

    @Override
    public void updateNote(Note note) {
        mNotes.getValue().set(0, note);
    }

    @Override
    public void deleteNote(Note note) {
        mNotes.getValue().remove(note);
    }

    @Override
    public void deleteAllNotes() {
        mNotes.getValue().clear();
    }

    @Override
    public LiveData<List<Note>> getAllNotes() {
        return mNotes;
    }


    @Override
    public void insertGitHubUser(GitHubUser gitHubUser) {
        mGitHubUsers.add(gitHubUser);
    }

    @Override
    public void updateGitHubUser(GitHubUser gitHubUser) {
        mGitHubUsers.set(0, gitHubUser);
    }

    @Override
    public void deleteGitHubUser(GitHubUser gitHubUser) {
        mGitHubUsers.remove(gitHubUser);
    }

    @Override
    public void deleteAllGitHubUsers() {
        mGitHubUsers.clear();
    }

    @Override
    public List<GitHubUser> getAllGitHubUsers() {
        return mGitHubUsers;
    }


    @Override
    public void getGitHubUsersRetrofit(MutableLiveData<List<GitHubUser>> liveDataGitHubUsers, String since) {

    }

    @Override
    public void getGitHubUsersRxJava(MutableLiveData<List<GitHubUser>> liveDataGitHubUsers, String since) {

    }
}
