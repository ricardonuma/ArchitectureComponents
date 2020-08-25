package com.example.ricardonuma.architecturecomponents.data.source.Local;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUserDao;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.NoteDao;

@Database(entities = {Note.class, GitHubUser.class},
        version = 1,
        exportSchema = false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    private static RoomDatabase INSTANCE;

    public abstract NoteDao noteDao();
    public abstract GitHubUserDao gitHubUserDao();

    public static synchronized RoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context,
                    RoomDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(mRoomDatabaseCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static Callback mRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao mNoteDao;
        private GitHubUserDao mGitHubUserDao;

        private PopulateDbAsyncTask(RoomDatabase db) {
            mNoteDao = db.noteDao();
            mGitHubUserDao = db.gitHubUserDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mNoteDao.insert(new Note("Title 1", "Description 1", 1));
            mNoteDao.insert(new Note("Title 2", "Description 2", 2));
            mNoteDao.insert(new Note("Title 3", "Description 3", 3));
            return null;
        }
    }
}
