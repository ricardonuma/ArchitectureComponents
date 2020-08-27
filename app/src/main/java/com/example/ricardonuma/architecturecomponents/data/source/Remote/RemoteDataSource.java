package com.example.ricardonuma.architecturecomponents.data.source.Remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ricardonuma.architecturecomponents.data.DataSource;
import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RemoteDataSource implements DataSource {

    GitHubServices mGithubServicesRetrofit;
    GitHubServices mGithubServicesRxJava;

    public RemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(GitHubServices.GITHUB_API_BASE_URL)
                .build();

        this.mGithubServicesRetrofit = retrofit.create(GitHubServices.class);


        Retrofit retrofitRxjava = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(GitHubServices.GITHUB_API_BASE_URL)
                .build();

        this.mGithubServicesRxJava = retrofitRxjava.create(GitHubServices.class);
    }

    @Override
    public void insertNote(Note note) {

    }

    @Override
    public void updateNote(Note note) {

    }

    @Override
    public void deleteNote(Note note) {

    }

    @Override
    public void deleteAllNotes() {

    }

    @Override
    public LiveData<List<Note>> getAllNotes() {
        return null;
    }


    @Override
    public void insertGitHubUser(GitHubUser gitHubUser) {

    }

    @Override
    public void updateGitHubUser(GitHubUser gitHubUser) {

    }

    @Override
    public void deleteGitHubUser(GitHubUser gitHubUser) {

    }

    @Override
    public void deleteAllGitHubUsers() {

    }

    @Override
    public List<GitHubUser> getAllGitHubUsers() {
        return null;
    }


    @Override
    public void getGitHubUsersRetrofit(final MutableLiveData<List<GitHubUser>> liveDataGitHubUsers, String since) {
        mGithubServicesRetrofit.getGitHubUsersRetrofit(since)
                .enqueue(new Callback<List<GitHubUser>>() {
                    @Override
                    public void onResponse(Call<List<GitHubUser>> call, Response<List<GitHubUser>> response) {
//                for (int i = 0; i < response.body().size(); i++) {
//                    gitHubUserViewModel.insert(response.body().get(i));
//                }
//                new GitHubUserViewModel.getAllGitHubUsersAsyncTask().execute();
                        liveDataGitHubUsers.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<GitHubUser>> call, Throwable e) {
                        Log.e("Erro: ", e.getMessage());
                    }
                });
    }

    @Override
    public void getGitHubUsersRxJava(final MutableLiveData<List<GitHubUser>> liveDataGitHubUsers, String since) {
        mGithubServicesRxJava.getGitHubUsersRxJava(since)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<List<GitHubUser>>() {
                    @Override
                    public void onError(Throwable e) {
                        Log.e("Erro: ", e.getMessage());
                    }

                    @Override
                    public void onSuccess(List<GitHubUser> gitHubUsers) {
//                        for (int i = 0; i < gitHubUsers.size(); i++) {
//                            mGitHubUserViewModel.insert(gitHubUsers.get(i));
//                        }
                        liveDataGitHubUsers.setValue(gitHubUsers);
                    }
                });
    }
}
