package com.example.ricardonuma.architecturecomponents.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import java.util.List;

import rx.Single;

public interface Repository {
    void insertNote(Note note);

    void updateNote(Note note);

    void deleteNote(Note note);

    void deleteAllNotes();

    MutableLiveData<List<Note>> getAllNotes();


    void insertGitHubUser(GitHubUser gitHubUser);

    void updateGitHubUser(GitHubUser gitHubUser);

    void deleteGitHubUser(GitHubUser gitHubUser);

    void deleteAllGitHubUsers();

    List<GitHubUser> getAllGitHubUsers();

    LiveData<List<GitHubUser>> usersCall(String since);

    Single<List<GitHubUser>> usersObservable(String since);
}
