package com.example.ricardonuma.architecturecomponents.ViewModel.GitHubUser;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ricardonuma.architecturecomponents.data.Repository;
import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;

import java.util.List;

import rx.Single;

public class GitHubUserViewModel extends ViewModel {

    private Repository mRepository;
    private static MutableLiveData<List<GitHubUser>> mLiveDataGitHubUsers = new MutableLiveData<>();

    public GitHubUserViewModel(Repository repository) {
        mRepository = repository;
        new getAllGitHubUsersAsyncTask(mRepository).execute();
    }

    public void insert(GitHubUser gitHubUser) {
        new insertGitHubUserAsyncTask(mRepository).execute(gitHubUser);
    }

    public void update(GitHubUser gitHubUser) {
        new updateGitHubUserAsyncTask(mRepository).execute(gitHubUser);
    }

    public void delete(GitHubUser gitHubUser) {
        new deleteGitHubUserAsyncTask(mRepository).execute(gitHubUser);
    }

    public void deleteAllUsers() {
        new deleteAllGitHubUsersAsyncTask(mRepository).execute();
    }

    public LiveData<List<GitHubUser>> getLiveDataAllGitHubUsers() {
        return mLiveDataGitHubUsers;
    }


    private static class insertGitHubUserAsyncTask extends AsyncTask<GitHubUser, Void, Void> {
        private static final String ROOM_ERROR = "ROOM ERROR";
        private Repository mRepository;

        private insertGitHubUserAsyncTask(Repository repository) {
            this.mRepository = repository;
        }

        @Override
        protected Void doInBackground(GitHubUser... gitHubUsers) {
            try {
                mRepository.insertGitHubUser(gitHubUsers[0]);
            } catch (SQLiteConstraintException e) {
                Log.e(ROOM_ERROR, e.getMessage());
            }
            return null;
        }
    }

    private static class updateGitHubUserAsyncTask extends AsyncTask<GitHubUser, Void, Void> {
        private Repository mRepository;

        private updateGitHubUserAsyncTask(Repository repository) {
            this.mRepository = repository;
        }

        @Override
        protected Void doInBackground(GitHubUser... gitHubUsers) {
            mRepository.updateGitHubUser(gitHubUsers[0]);
            return null;
        }
    }

    private static class deleteGitHubUserAsyncTask extends AsyncTask<GitHubUser, Void, Void> {
        private Repository mRepository;

        private deleteGitHubUserAsyncTask(Repository repository) {
            this.mRepository = repository;
        }

        @Override
        protected Void doInBackground(GitHubUser... gitHubUsers) {
            mRepository.deleteGitHubUser(gitHubUsers[0]);
            return null;
        }
    }

    private static class deleteAllGitHubUsersAsyncTask extends AsyncTask<Void, Void, Void> {
        private Repository mRepository;

        private deleteAllGitHubUsersAsyncTask(Repository repository) {
            this.mRepository = repository;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mRepository.deleteAllGitHubUsers();
            return null;
        }
    }

    private static class getAllGitHubUsersAsyncTask extends AsyncTask<Void, Void, List<GitHubUser>> {
        private Repository mRepository;

        private getAllGitHubUsersAsyncTask(Repository repository) {
            this.mRepository = repository;
        }

        @Override
        protected List<GitHubUser> doInBackground(Void... voids) {
            return mRepository.getAllGitHubUsers();
        }

        @Override
        protected void onPostExecute(List<GitHubUser> gitHubUsers) {
            super.onPostExecute(gitHubUsers);
            mLiveDataGitHubUsers.setValue(gitHubUsers);
        }
    }


    public void usersCall(String since) {
        mRepository.usersCall(since);
    }

    public Single<List<GitHubUser>> usersObservable(String since) {
        return mRepository.usersObservable(since);
    }
}
