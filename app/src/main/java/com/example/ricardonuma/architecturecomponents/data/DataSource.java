package com.example.ricardonuma.architecturecomponents.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import java.util.List;

import retrofit2.http.Query;
import rx.Single;

public interface DataSource {

    // Local Source

    // Note
    void insertNote(Note note);

    void updateNote(Note note);

    void deleteNote(Note note);

    void deleteAllNotes();

    MutableLiveData<List<Note>> getAllNotes();


    // GitHubUser
    void insertGitHubUser(GitHubUser gitHubUser);

    void updateGitHubUser(GitHubUser gitHubUser);

    void deleteGitHubUser(GitHubUser gitHubUser);

    void deleteAllGitHubUsers();

    List<GitHubUser> getAllGitHubUsers();


    // Remote Source

    // GitHubUser
    LiveData<List<GitHubUser>> usersCall(@retrofit2.http.Query("since") String since);

    Single<List<GitHubUser>> usersObservable(@Query("since") String since);
}
