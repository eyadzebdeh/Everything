package com.eyad.everything.presentation.movieslist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.eyad.everything.R
import com.eyad.everything.domain.model.MoviesResponse
import com.eyad.everything.presentation.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4ClassRunner::class)
class MoviesFragmentTest{

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Mock
    lateinit var viewModel: MoviesViewModel

    @Test
    fun test_fetchMoviesSuccess_moviesShown() {
        viewModel.moviesLiveData.value = getFilledMoviesList()
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
    }

    private fun getFilledMoviesList(): MoviesResponse {
        val movies = listOf("first movie", "second movie")
        return MoviesResponse(1, movies)
    }

}


