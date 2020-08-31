package com.example.ricardonuma.architecturecomponents.data.source.Local;

import androidx.room.Database;

import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUserDao;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.NoteDao;

@Database(entities = {Note.class, GitHubUser.class},
        version = 1,
        exportSchema = false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    public abstract NoteDao noteDao();

    public abstract GitHubUserDao gitHubUserDao();
}
