package com.example.ricardonuma.architecturecomponents;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ricardonuma.architecturecomponents.data.DataSource;
import com.example.ricardonuma.architecturecomponents.data.DefaultRepository;
import com.example.ricardonuma.architecturecomponents.data.Repository;
import com.example.ricardonuma.architecturecomponents.data.source.Local.LocalDataSource;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.NoteDao;
import com.example.ricardonuma.architecturecomponents.data.source.Local.RoomDatabase;
import com.example.ricardonuma.architecturecomponents.data.source.Remote.RemoteDataSource;


public class ServiceLocator {

    private static RoomDatabase database = null;
    private static Object lock = new Object();

    public static Repository repository = null;

    public Repository provideRepository(Context context) {
        synchronized (this) {
            return repository != null ? repository : createRepository(context);
        }
    }

    private Repository createRepository(Context context) {
        Repository newRepo = new DefaultRepository(createLocalDataSource(context), createRemoteDataSource());
        repository = newRepo;
        return newRepo;
    }

    private DataSource createLocalDataSource(Context context) {
        RoomDatabase newDatabase = database != null ? database : createDatabase(context);
        return new LocalDataSource(newDatabase.noteDao(), newDatabase.gitHubUserDao());
    }

    private RoomDatabase createDatabase(Context context) {
        RoomDatabase result = Room.databaseBuilder(context,
                RoomDatabase.class, "note_database")
                .fallbackToDestructiveMigration()
                .addCallback(mRoomDatabaseCallback)
                .build();
        database = result;
        return result;
    }

    public static androidx.room.RoomDatabase.Callback mRoomDatabaseCallback = new androidx.room.RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(database).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao mNoteDao;

        private PopulateDbAsyncTask(RoomDatabase db) {
            mNoteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mNoteDao.insert(new Note("Title 1", "Description 1", 1));
            mNoteDao.insert(new Note("Title 2", "Description 2", 2));
            mNoteDao.insert(new Note("Title 3", "Description 3", 3));
            return null;
        }
    }

    private DataSource createRemoteDataSource() {
        return new RemoteDataSource();
    }

    @VisibleForTesting
    public static void resetRepository() {
        synchronized (lock) {
//            runBlocking {
//                TasksRemoteDataSource.deleteAllTasks()
//            }
            // Clear all data to avoid test pollution.
            if (database != null) {
                database.clearAllTables();
                database.close();
            }
            database = null;
            repository = null;
        }
    }

    @VisibleForTesting
    public static void setRepository(Repository repository) {
        ServiceLocator.repository = repository;
    }
}
