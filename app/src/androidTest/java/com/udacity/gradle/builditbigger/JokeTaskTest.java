package com.udacity.gradle.builditbigger;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.regex.Matcher;

@RunWith(AndroidJUnit4.class)
public class JokeTaskTest {

    @Rule
    public final ActivityTestRule activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void before() {

    }

    @Test
    public void test() {
        Espresso.onView(ViewMatchers.withId(R.id.button_tel_joke))
                .perform(ViewActions.click());

        // TODO: add idling resources to the async task class

//        Intents.intended(IntentMatchers.hasComponent(.class.getName()));
//        Intents.intended(IntentMatchers.hasExtra(,));

//        Espresso.onView(ViewMatchers.withId(R.id.joke_text))
//                .check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))));
    }

    @After
    public void after() {

    }
}
