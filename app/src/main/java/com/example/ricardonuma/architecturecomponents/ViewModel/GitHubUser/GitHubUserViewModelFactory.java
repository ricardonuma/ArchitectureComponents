package com.example.ricardonuma.architecturecomponents.ViewModel.GitHubUser;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ricardonuma.architecturecomponents.data.Repository;

public class GitHubUserViewModelFactory implements ViewModelProvider.Factory {

    private final Repository mRepository;

    public GitHubUserViewModelFactory(Repository repository) {
        this.mRepository = repository;
    }

    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GitHubUserViewModel.class)) {
            return (T) new GitHubUserViewModel(mRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

