package com.udacity.gradle.builditbigger;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.idlingresources.EspressoIdlingResource;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ladsoft.com.jokedisplay.activity.JokeDisplayActivity;

@RunWith(AndroidJUnit4.class)
public class JokeTaskTest {

    @Rule
    public final ActivityTestRule activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void before() {
        Intents.init();
        IdlingRegistry.getInstance()
                .register(EspressoIdlingResource.countingIdlingResource);
    }

    @Test
    public void testNotEmptyJokeContent() {
        Espresso.onView(ViewMatchers.withId(R.id.button_tel_joke))
                .perform(ViewActions.click());

        Intents.intended(IntentMatchers.hasComponent(JokeDisplayActivity.class.getName()));
        Intents.intended(IntentMatchers.hasExtra(Matchers.is(
                                                    JokeDisplayActivity.EXTRA_PROVIDED_JOKE),
                                                    Matchers.not(Matchers.isEmptyString())
                                                )
                                    );

        Espresso.onView(ViewMatchers.withId(R.id.joke))
                .check(ViewAssertions.matches(
                                            Matchers.not(ViewMatchers.withText(""))
                                        )
                                    );
    }

    @After
    public void after() {
        IdlingRegistry.getInstance()
                .unregister(EspressoIdlingResource.countingIdlingResource);
        Intents.release();
    }
}
