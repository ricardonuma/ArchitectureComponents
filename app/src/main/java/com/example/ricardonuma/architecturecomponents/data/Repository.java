package com.example.ricardonuma.architecturecomponents.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import java.util.List;

public interface Repository {
    void insertNote(Note note);

    void updateNote(Note note);

    void deleteNote(Note note);

    void deleteAllNotes();

    LiveData<List<Note>> getAllNotes();


    void insertGitHubUser(GitHubUser gitHubUser);

    void updateGitHubUser(GitHubUser gitHubUser);

    void deleteGitHubUser(GitHubUser gitHubUser);

    void deleteAllGitHubUsers();

    List<GitHubUser> getAllGitHubUsers();


    void getGitHubUsersRetrofit(MutableLiveData<List<GitHubUser>> liveDataGitHubUsers, String since);

    void getGitHubUsersRxJava(MutableLiveData<List<GitHubUser>> liveDataGitHubUsers, String since);
}
