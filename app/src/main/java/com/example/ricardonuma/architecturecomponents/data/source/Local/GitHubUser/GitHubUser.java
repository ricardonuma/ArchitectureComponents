package com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "github_user_table",
        indices = {@Index(value = {"server_id", "login", "repos_url"},
                unique = true)})
public class GitHubUser {

    @PrimaryKey(autoGenerate = true)
    int local_id;

    @ColumnInfo(name = "server_id")
    int id;
    String login;
    String repos_url;

    public int getLocal_id() {
        return local_id;
    }

    public void setLocal_id(int local_id) {
        this.local_id = local_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }
}
