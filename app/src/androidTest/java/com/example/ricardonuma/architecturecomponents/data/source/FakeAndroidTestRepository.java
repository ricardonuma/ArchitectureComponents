package com.example.ricardonuma.architecturecomponents.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ricardonuma.architecturecomponents.data.Repository;
import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import java.util.ArrayList;
import java.util.List;

public class FakeAndroidTestRepository implements Repository {

    private MutableLiveData<List<Note>> mNotes = new MutableLiveData<>();
    private List<Note> mNoteList = new ArrayList<>();
    private List<GitHubUser> mGitHubUsers = new ArrayList<>();

    public FakeAndroidTestRepository(List<Note> notes) {
        mNoteList = notes;
    }

    public void refreshNotes() {
        mNotes.postValue(mNoteList);
    }

    @Override
    public void insertNote(Note note) {
        mNoteList.add(note);
        refreshNotes();
    }

    @Override
    public void updateNote(Note note) {
        mNoteList.set(0, note);
        refreshNotes();
    }

    @Override
    public void deleteNote(Note note) {
        mNoteList.remove(note);
        refreshNotes();
    }

    @Override
    public void deleteAllNotes() {
        mNoteList.clear();
        refreshNotes();
    }

    @Override
    public LiveData<List<Note>> getAllNotes() {
        refreshNotes();
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
