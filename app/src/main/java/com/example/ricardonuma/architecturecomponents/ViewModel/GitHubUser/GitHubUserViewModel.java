package com.example.ricardonuma.architecturecomponents.ViewModel.GitHubUser;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ricardonuma.architecturecomponents.data.Repository;
import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;

import java.util.List;

public class GitHubUserViewModel extends ViewModel {

    private static Repository mRepository;
    private static MutableLiveData<List<GitHubUser>> mLiveDataGitHubUsers = new MutableLiveData<>();

    public GitHubUserViewModel(Repository repository) {
        mRepository = repository;
    }

    public void insertGitHubUser(GitHubUser gitHubUser) {
        mRepository.insertGitHubUser(gitHubUser);
    }

    public void updateGitHubUser(GitHubUser gitHubUser) {
        mRepository.updateGitHubUser(gitHubUser);
    }

    public void deleteGitHubUser(GitHubUser gitHubUser) {
        mRepository.deleteGitHubUser(gitHubUser);
    }

    public void deleteAllUsers() {
        mRepository.deleteAllGitHubUsers();
    }

    public MutableLiveData<List<GitHubUser>> observeAllGitHubUsers() {
        return mLiveDataGitHubUsers;
    }

    public void getAllGitHubUsers() {
        mLiveDataGitHubUsers.setValue(mRepository.getAllGitHubUsers());
    }


    public void getGitHubUsersRetrofit(String since) {
        mRepository.getGitHubUsersRetrofit(mLiveDataGitHubUsers, since);
    }

    public void getGitHubUsersRxJava(String since) {
        mRepository.getGitHubUsersRxJava(mLiveDataGitHubUsers, since);
    }
}
