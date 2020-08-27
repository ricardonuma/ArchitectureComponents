package com.example.ricardonuma.architecturecomponents.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import java.util.List;

import retrofit2.http.Query;

public interface DataSource {

    // Local Source

    // Note
    void insertNote(Note note);

    void updateNote(Note note);

    void deleteNote(Note note);

    void deleteAllNotes();

    LiveData<List<Note>> getAllNotes();


    // GitHubUser
    void insertGitHubUser(GitHubUser gitHubUser);

    void updateGitHubUser(GitHubUser gitHubUser);

    void deleteGitHubUser(GitHubUser gitHubUser);

    void deleteAllGitHubUsers();

    List<GitHubUser> getAllGitHubUsers();


    // Remote Source

    // GitHubUser
    void getGitHubUsersRetrofit(MutableLiveData<List<GitHubUser>> liveDataGitHubUsers, @Query("since") String since);

    void getGitHubUsersRxJava(MutableLiveData<List<GitHubUser>> liveDataGitHubUsers, @Query("since") String since);
}
