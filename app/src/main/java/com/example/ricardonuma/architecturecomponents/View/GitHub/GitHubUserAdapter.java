package com.example.ricardonuma.architecturecomponents.View.GitHub;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.R;

import java.util.ArrayList;
import java.util.List;

public class GitHubUserAdapter extends RecyclerView.Adapter<GitHubUserAdapter.GitHubUserHolder> {

    private final LayoutInflater mInflater;
    private List<GitHubUser> mGitHubUsers = new ArrayList<>();

    public GitHubUserAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public GitHubUserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new GitHubUserHolder(mInflater.inflate(R.layout.github_user_item_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GitHubUserHolder gitHubUserHolder, int position) {
        GitHubUser gitHubUser = mGitHubUsers.get(position);
        gitHubUserHolder.login.setText(gitHubUser.getLogin());
        gitHubUserHolder.repos_url.setText(gitHubUser.getRepos_url());
        gitHubUserHolder.id.setText(String.valueOf(gitHubUser.getId()));
    }

    public void setGitHubUsers(List<GitHubUser> gitHubUserList) {
        this.mGitHubUsers = gitHubUserList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mGitHubUsers != null) {
            return mGitHubUsers.size();
        } else {
            return 0;
        }
    }

    class GitHubUserHolder extends RecyclerView.ViewHolder {
        TextView login;
        TextView repos_url;
        TextView id;

        public GitHubUserHolder(@NonNull View itemView) {
            super(itemView);

            login = itemView.findViewById(R.id.text_view_login);
            repos_url = itemView.findViewById(R.id.text_view_repos_url);
            id = itemView.findViewById(R.id.text_view_id);
        }
    }
}
