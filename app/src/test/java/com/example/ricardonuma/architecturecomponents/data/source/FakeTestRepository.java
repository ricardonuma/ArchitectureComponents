package com.example.ricardonuma.architecturecomponents.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ricardonuma.architecturecomponents.data.Repository;
import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import java.util.List;

import rx.Single;

public class FakeTestRepository implements Repository {

    private MutableLiveData<List<Note>> mNotes;
    private List<GitHubUser> mGitHubUsers;

    public FakeTestRepository(MutableLiveData<List<Note>> mNotes, List<GitHubUser> mGitHubUsers) {
        this.mNotes = mNotes;
        this.mGitHubUsers = mGitHubUsers;
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
    public MutableLiveData<List<Note>> getAllNotes() {
        return mNotes;
    }



    @Override
    public void insertGitHubUser(GitHubUser gitHubUser) {
        mGitHubUsers.add(gitHubUser);
    }

    @Override
    public void updateGitHubUser(GitHubUser gitHubUser) {
//        mGitHubUsers.set(gitHubUser);
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
    public LiveData<List<GitHubUser>> usersCall(String since) {
        return null;
    }

    @Override
    public Single<List<GitHubUser>> usersObservable(String since) {
        return null;
    }
}
