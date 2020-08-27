package com.example.ricardonuma.architecturecomponents.ViewModel.Note;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ricardonuma.architecturecomponents.data.source.FakeTestRepository;
import com.example.ricardonuma.architecturecomponents.data.source.Local.GitHubUser.GitHubUser;
import com.example.ricardonuma.architecturecomponents.data.source.Local.Note.Note;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.ricardonuma.architecturecomponents.LiveDataTestUtil.getOrAwaitValue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class NoteViewModelTest {

    FakeTestRepository mFakeTestRepository;

    // Subject under test
    private NoteViewModel mNoteViewModel;

    Note mNote1 = new Note("title1", "description1", 1);
    Note mNote2 = new Note("title2", "description2", 2);
    Note mNewNote = new Note("title3", "description3", 3);

    MutableLiveData<List<Note>> mNotes = new MutableLiveData<>();
    List<Note> mNoteList = new ArrayList<>();
    List<GitHubUser> mGitHubUsers = new ArrayList<>();

    // Executes each task synchronously using Architecture Components.
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setupViewModel() {
        mNoteList.add(mNote1);
        mNoteList.add(mNote2);

        mNotes.setValue(mNoteList);

        mFakeTestRepository = new FakeTestRepository(mNotes, mGitHubUsers);

        mNoteViewModel = new NoteViewModel(mFakeTestRepository);
    }

    @Test
    public void insertNote_insertNoteFromRepository() {
        // When adding a new note
        mNoteViewModel.insertNote(mNewNote);

        // Then the new note event is triggered
        assertThat(mNotes.getValue(), hasItem(mNewNote));
    }

    @Test
    public void updateNote_updateNoteFromRepository() {
        mNote2.setTitle("title3");
        mNote2.setDescription("description3");
        mNote2.setPriority(3);

        mNoteViewModel.updateNote(mNote2);

        assertThat(mNotes.getValue(),
                hasItem(allOf(
                        Matchers.<Note>hasProperty("title", is("title3")),
                        Matchers.<Note>hasProperty("description", is("description3")),
                        Matchers.<Note>hasProperty("priority", is(3)))));
    }

    @Test
    public void deleteNote_deleteNoteFromRepository() {
        // When adding a new note
        mNoteViewModel.deleteNote(mNote2);

        // Then the new note event is triggered
        assertThat(mNotes.getValue(), not(hasItem(mNote2)));
    }

    @Test
    public void deleteAllNotes_deleteAllNotesFromRepository() {
        // When adding a new note
        mNoteViewModel.deleteAllNotes();

        // Then the new note event is triggered
        assertThat(mNotes.getValue(), is(Matchers.<Note>empty()));
    }

    @Test
    public void getAllNotes_getAllNotesFromRepository_returnAllNotes() throws InterruptedException {
        // When adding a new note
        LiveData<List<Note>> liveDataNotes = mNoteViewModel.getAllNotes();

        // Then the new note event is triggered
        assertThat(getOrAwaitValue(liveDataNotes), is(mNotes.getValue()));
    }
}