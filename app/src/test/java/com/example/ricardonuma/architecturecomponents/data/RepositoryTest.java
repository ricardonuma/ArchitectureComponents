package com.example.ricardonuma.architecturecomponents.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ricardonuma.architecturecomponents.data.source.FakeDataSource;
import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class RepositoryTest {

    Note mNote1 = new Note("title1", "description1", 1);
    Note mNote2 = new Note("title2", "description2", 2);
    Note mNewNote = new Note("title3", "description3", 3);

    GitHubUser mGitHubUser1 = new GitHubUser();
    GitHubUser mGitHubUser2 = new GitHubUser();
    GitHubUser mNewGitHubUser = new GitHubUser();

    List<Note> mNotes = new ArrayList<>();
    MutableLiveData<List<Note>> mLiveDataNotes = new MutableLiveData<>();
    List<GitHubUser> mGitHubUsers = new ArrayList<>();
    MutableLiveData<List<GitHubUser>> mLiveDataGitHubUsers = new MutableLiveData<>();

    DefaultRepository mRepository;
    FakeDataSource mLocalDataSource;
    FakeDataSource mRemoteDataSource;
    private static final String SINCE = "3";

    // Executes each task synchronously using Architecture Components.
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() throws Exception {

        mNotes.add(mNote1);
        mNotes.add(mNote2);

        mLiveDataNotes.setValue(mNotes);

        mGitHubUser1.setId(1);
        mGitHubUser1.setLogin("login1");
        mGitHubUser1.setRepos_url("repos_url1");
        mGitHubUser2.setId(2);
        mGitHubUser2.setLogin("login2");
        mGitHubUser2.setRepos_url("repos_url2");
        mNewGitHubUser.setId(3);
        mNewGitHubUser.setLogin("login3");
        mNewGitHubUser.setRepos_url("repos_url3");

        mGitHubUsers.add(mGitHubUser1);
        mGitHubUsers.add(mGitHubUser2);

        mLiveDataGitHubUsers.setValue(mGitHubUsers);

        mLocalDataSource = new FakeDataSource(mLiveDataNotes, mGitHubUsers);

        mRemoteDataSource = new FakeDataSource(mLiveDataGitHubUsers);

        mRepository = new DefaultRepository(mLocalDataSource, mRemoteDataSource);
    }

    @Test
    public void insertNote_insertNoteFromLocalDataSource() {
        mRepository.insertNote(mNewNote);

        assertThat(mLiveDataNotes.getValue(), hasItem(mNewNote));
    }

    @Test
    public void updateNote_updateNoteFromLocalDataSource() {
        mNote2.setTitle("title3");
        mNote2.setDescription("description3");
        mNote2.setPriority(3);

        mRepository.updateNote(mNote2);

        assertThat(mLiveDataNotes.getValue(),
                hasItem(allOf(
                        Matchers.<Note>hasProperty("title", is("title3")),
                        Matchers.<Note>hasProperty("description", is("description3")),
                        Matchers.<Note>hasProperty("priority", is(3)))));
    }

    @Test
    public void deleteNote_deleteNoteFromLocalDataSource() {
        mRepository.deleteNote(mNote2);

        assertThat(mLiveDataNotes.getValue(), not(hasItem(mNote2)));
    }

    @Test
    public void deleteAllNotes_deleteAllNotesFromLocalDataSource() {
        mRepository.deleteAllNotes();

        assertThat(mLiveDataNotes.getValue(), is(Matchers.<Note>empty()));
    }

    @Test
    public void getAllNotes_getAllNotesFromLocalDataSource() {
        LiveData<List<Note>> liveDataNotes = mRepository.getAllNotes();

        assertThat(liveDataNotes.getValue(), is(mLiveDataNotes.getValue()));
    }

    @Test
    public void insertGitHubUser_insertGitHubUserFromLocalDataSource() {
        mRepository.insertGitHubUser(mNewGitHubUser);

        assertThat(mGitHubUsers, hasItem(mNewGitHubUser));
    }

    @Test
    public void updateGitHubUser_updateGitHubUserFromLocalDataSource() {
        mGitHubUser2.setId(3);
        mGitHubUser2.setLogin("login3");
        mGitHubUser2.setRepos_url("repos_url3");

        mRepository.updateGitHubUser(mGitHubUser2);

        assertThat(mGitHubUsers,
                hasItem(allOf(
                        Matchers.<GitHubUser>hasProperty("id", is(3)),
                        Matchers.<GitHubUser>hasProperty("login", is("login3")),
                        Matchers.<GitHubUser>hasProperty("repos_url", is("repos_url3")))));
    }

    @Test
    public void deleteGitHubUser_deleteGitHubUserFromLocalDataSource() {
        mRepository.deleteGitHubUser(mGitHubUser2);

        assertThat(mGitHubUsers, not(hasItem(mGitHubUser2)));
    }

    @Test
    public void deleteAllGitHubUsers_deleteAllGitHubUsersFromLocalDataSource() {
        mRepository.deleteAllGitHubUsers();

        assertThat(mGitHubUsers, is(Matchers.<GitHubUser>empty()));
    }

    @Test
    public void getAllGitHubUsers_getAllGitHubUsersFromLocalDataSource() {
        List<GitHubUser> gitHubUsers = mRepository.getAllGitHubUsers();

        assertThat(gitHubUsers, is(mLiveDataGitHubUsers.getValue()));
    }

    @Test
    public void usersCall_requestsAllGitHubUsersFromRemoteDataSource() {
        mRepository.getGitHubUsersRetrofit(mLiveDataGitHubUsers, SINCE);

        assertThat(mLiveDataGitHubUsers.getValue(), is(mGitHubUsers));
    }

    @Test
    public void usersObservable_requestsAllGitHubUsersFromRemoteDataSource() {
        mRepository.getGitHubUsersRxJava(mLiveDataGitHubUsers, SINCE);

        assertThat(mLiveDataGitHubUsers.getValue(), is(mGitHubUsers));
    }
}