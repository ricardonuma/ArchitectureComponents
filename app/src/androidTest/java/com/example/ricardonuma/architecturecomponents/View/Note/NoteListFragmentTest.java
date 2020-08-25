//package com.example.ricardonuma.architecturecomponents.View.Note;
//
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//
//import androidx.test.rule.ActivityTestRule;
//import com.example.ricardonuma.architecturecomponents.R;
//import com.example.ricardonuma.architecturecomponents.View.MainActivity;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.withText;
//
//@RunWith(AndroidJUnit4.class)
//public class NoteListFragmentTest {
//
//    @Rule
//    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
//
//
//    @Before
//    public void init(){
//        activityActivityTestRule.getActivity()
//                .getSupportFragmentManager().beginTransaction();
//    }
//
//    @Test
//    public void shouldUpdateTextAfterButtonClick() {
//
//        onView(withId(R.id.recycler_view))
//                .check(matches(isDisplayed()));
//
//        onView(withId(R.id.next))
//                .check(matches(isDisplayed()));
//
//        onView(withId(R.id.next))
//                .check(matches(withText("Next")));
//
////        onView(withId(R.id.next)).perform(click());
////
////        onView(withId(R.id.text)).check(matches(withText("Hello World!")));
//    }
//}