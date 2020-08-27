package com.example.ricardonuma.architecturecomponents.ViewModel.GitHubUser;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ricardonuma.architecturecomponents.data.source.FakeTestRepository;
import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.ricardonuma.architecturecomponents.LiveDataTestUtil.getOrAwaitValue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class GitHubUserViewModelTest {

    private static final String SINCE = "3";

    FakeTestRepository mFakeTestRepository;

    // Subject under test
    private GitHubUserViewModel mGitHubUserViewModel;

    MutableLiveData<List<Note>> mNotes = new MutableLiveData<>();
    List<GitHubUser> mGitHubUsers = new ArrayList<>();
    MutableLiveData<List<GitHubUser>> mLiveDataGitHubUsers = new MutableLiveData<>();

    GitHubUser mGitHubUser1 = new GitHubUser();
    GitHubUser mGitHubUser2 = new GitHubUser();
    GitHubUser mNewGitHubUser = new GitHubUser();

    // Executes each gitHubUser synchronously using Architecture Components.
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() throws Exception {
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

        mFakeTestRepository = new FakeTestRepository(mNotes, mGitHubUsers);

        mGitHubUserViewModel = new GitHubUserViewModel(mFakeTestRepository);
    }

    @Test
    public void insertGitHubUser_insertGitHubUserFromRepository() {
        // When adding a new gitHubUser
        mGitHubUserViewModel.insertGitHubUser(mNewGitHubUser);

        // Then the new gitHubUser event is triggered
        assertThat(mGitHubUsers, hasItem(mNewGitHubUser));
    }

    @Test
    public void updateGitHubUser_updateGitHubUserFromRepository() {
        mGitHubUser2.setId(3);
        mGitHubUser2.setLogin("login3");
        mGitHubUser2.setRepos_url("repos_url3");

        mGitHubUserViewModel.updateGitHubUser(mGitHubUser2);

        assertThat(mGitHubUsers,
                hasItem(allOf(
                        Matchers.<GitHubUser>hasProperty("id", is(3)),
                        Matchers.<GitHubUser>hasProperty("login", is("login3")),
                        Matchers.<GitHubUser>hasProperty("repos_url", is("repos_url3")))));
    }

    @Test
    public void deleteGitHubUser_deleteGitHubUserFromRepository() {
        // When adding a new gitHubUser
        mGitHubUserViewModel.deleteGitHubUser(mNewGitHubUser);

        // Then the new gitHubUser event is triggered
        assertThat(mGitHubUsers, not(hasItem(mNewGitHubUser)));
    }

    @Test
    public void deleteAllGitHubUsers_deleteAllGitHubUsersFromRepository() {
        // When adding a new gitHubUser
        mGitHubUserViewModel.deleteAllUsers();

        // Then the new gitHubUser event is triggered
        assertThat(mGitHubUsers, is(Matchers.<GitHubUser>empty()));
    }

    @Test
    public void getAllGitHubUsers_getGitHubUsersFromRepository() throws InterruptedException {
        // When adding a new gitHubUser
        LiveData<List<GitHubUser>> gitHubUsers = mGitHubUserViewModel.observeAllGitHubUsers();
        mGitHubUserViewModel.getAllGitHubUsers();

        // Then the new gitHubUser event is triggered
        assertThat(getOrAwaitValue(gitHubUsers), is(mLiveDataGitHubUsers.getValue()));
    }

    @Test
    public void getGitHubUsersRetrofit_getGitHubUsersRetrofitFromRepository() {
        // When adding a new gitHubUser
        mGitHubUserViewModel.getGitHubUsersRetrofit(SINCE);

        // Then the new gitHubUser event is triggered
        assertThat(mLiveDataGitHubUsers.getValue(), is(mGitHubUsers));
    }

    @Test
    public void getGitHubUsersRxJava_getGitHubUsersRxJavaFromRepository() {
        // When adding a new gitHubUser
        mGitHubUserViewModel.getGitHubUsersRxJava(SINCE);

        // Then the new gitHubUser event is triggered
        assertThat(mLiveDataGitHubUsers.getValue(), is(mGitHubUsers));
    }
}