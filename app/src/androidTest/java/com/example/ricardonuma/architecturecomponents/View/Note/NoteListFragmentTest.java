package com.example.ricardonuma.architecturecomponents.View.Note;

import android.os.Bundle;
import android.test.suitebuilder.annotation.MediumTest;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.ricardonuma.architecturecomponents.R;
import com.example.ricardonuma.architecturecomponents.ServiceLocator;
import com.example.ricardonuma.architecturecomponents.data.Repository;
import com.example.ricardonuma.architecturecomponents.data.source.FakeAndroidTestRepository;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class NoteListFragmentTest {

    private Repository repository;

    @Before
    public void initRepository() {
        Note mNote1 = new Note("title1", "description1", 1);
        Note mNote2 = new Note("title2", "description2", 2);
        Note mNewNote = new Note("title3", "description3", 3);
        List<Note> mNoteList = new ArrayList<>();
        mNoteList.add(mNote1);
        mNoteList.add(mNote2);
//        repository.insertNote(mNote1);
//        repository.insertNote(mNote2);

        repository = new FakeAndroidTestRepository(mNoteList);

        ServiceLocator.setRepository(repository);
    }

    @After
    public void cleanupDb() {
        ServiceLocator.resetRepository();
    }

    @Test
    public void activeNoteList_DisplayedInUi() throws InterruptedException {
        // WHEN - Details fragment launched to display task
        Bundle bundle = new NoteListFragmentArgs().toBundle();
        FragmentScenario.launchInContainer(NoteListFragment.class, bundle, R.style.AppTheme, null);

        Thread.sleep(3000);
    }
}