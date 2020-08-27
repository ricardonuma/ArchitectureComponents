package com.example.ricardonuma.architecturecomponents.View.GitHub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ricardonuma.architecturecomponents.R;
import com.example.ricardonuma.architecturecomponents.ViewModel.GitHubUser.GitHubUserViewModel;
import com.example.ricardonuma.architecturecomponents.ViewModel.GitHubUser.GitHubUserViewModelFactory;
import com.example.ricardonuma.architecturecomponents.data.DefaultRepository;
import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;

import java.util.List;

public class GitHubRepositoriesFragment extends Fragment {

    private static final String SINCE = "3";

    private GitHubUserViewModel mGitHubUserViewModel;
    private RecyclerView mRecyclerView;
    private GitHubUserAdapter mGitHubUserAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_github_repositories, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Button retrofit = getView().findViewById(R.id.retrofit);
        retrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit();
            }
        });

        Button rxjava = getView().findViewById(R.id.rxjava);
        rxjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rxjava();
            }
        });


        mRecyclerView = getView().findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

        mGitHubUserAdapter = new GitHubUserAdapter(getContext());
        mRecyclerView.setAdapter(mGitHubUserAdapter);

        GitHubUserViewModelFactory gitHubUserViewModelFactory =
                new GitHubUserViewModelFactory(DefaultRepository.getRepository(requireActivity().getApplication()));
        mGitHubUserViewModel = ViewModelProviders.of(this, gitHubUserViewModelFactory).get(GitHubUserViewModel.class);
        mGitHubUserViewModel.observeAllGitHubUsers().observe(getActivity(), new androidx.lifecycle.Observer<List<GitHubUser>>() {
            @Override
            public void onChanged(@Nullable List<GitHubUser> gitHubUsers) {
                mGitHubUserAdapter.setGitHubUsers(gitHubUsers);
            }
        });
    }

    private void retrofit() {
        mGitHubUserViewModel.getGitHubUsersRetrofit(SINCE);
    }

    private void rxjava() {
        mGitHubUserViewModel.getGitHubUsersRxJava(SINCE);
    }
}
