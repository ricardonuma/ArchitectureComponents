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
    void insert(GitHubUser gitHubUser);

    @Update
    void update(GitHubUser gitHubUser);

    @Delete
    void delete(GitHubUser gitHubUser);

    @Query("DELETE FROM github_user_table")
    void deleteAllGitHubUsers();

    @Query("SELECT * FROM github_user_table ORDER BY local_id ASC")
    List<GitHubUser> getAllGitHubUsers();
}
