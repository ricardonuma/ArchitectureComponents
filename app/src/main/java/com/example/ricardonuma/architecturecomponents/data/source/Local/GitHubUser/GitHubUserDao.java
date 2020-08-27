package com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GitHubUserDao {

    @Insert
    void insertGitHubUser(GitHubUser gitHubUser);

    @Update
    void updateGitHubUser(GitHubUser gitHubUser);

    @Delete
    void deleteGitHubUser(GitHubUser gitHubUser);

    @Query("DELETE FROM github_user_table")
    void deleteAllGitHubUsers();

    @Query("SELECT * FROM github_user_table ORDER BY local_id ASC")
    List<GitHubUser> getAllGitHubUsers();
}
