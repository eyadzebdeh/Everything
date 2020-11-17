package com.eyad.everything.presentation.movieslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.eyad.everything.TestCoroutineRule
import com.eyad.everything.domain.model.MoviesResponse
import com.eyad.everything.domain.usecase.MoviesUseCase
import kotlinx.coroutines.*
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest{

    private lateinit var moviesViewModel: MoviesViewModel

    @Mock
    private lateinit var useCase: MoviesUseCase


    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp(){
        moviesViewModel = MoviesViewModel(useCase)
    }

    @Test
    fun fetchMovies_firstFetchSuccessWithItems_resultsSent(){
        testCoroutineRule.runBlockingTest {
            `when`(useCase.execute()).thenReturn(getFilledMoviesList())
            moviesViewModel.fetchMovies()
            assertThat(moviesViewModel.moviesLiveData.value, `is`(getFilledMoviesList()))
        }
    }

    @Test
    fun fetchMovies_firstFetchSuccessWithoutItems_emptySent(){
        testCoroutineRule.runBlockingTest {
            `when`(useCase.execute()).thenReturn(getEmptyMoviesList())
            moviesViewModel.fetchMovies()
            assertThat(moviesViewModel.moviesLiveData.value, `is`(nullValue()))
            assertThat(moviesViewModel.emptyLiveData.value, `is`(1))
        }
    }

    @Test
    fun fetchMovies_firstFetchFailure_failureSent(){
        testCoroutineRule.runBlockingTest {
            `when`(useCase.execute()).thenThrow(getException())
            moviesViewModel.fetchMovies()
            assertThat(moviesViewModel.moviesLiveData.value, `is`(nullValue()))
            assertThat(moviesViewModel.emptyLiveData.value, `is`(nullValue()))
            assertThat(moviesViewModel.failureLiveData?.value, `is`(notNullValue()))
        }
    }

    @Test
    fun fetchMovies_notFirstFetchSuccessWithItems_resultsSent(){
        testCoroutineRule.runBlockingTest {
            `when`(useCase.execute(PAGE_NUM)).thenReturn(getFilledMoviesListOtherPage())
            moviesViewModel.fetchMovies(PAGE_NUM)
            assertThat(moviesViewModel.moviesLiveData.value, `is`(notNullValue()))
        }
    }

    @Test
    fun fetchMovies_notFirstFetchSuccessWithoutItems_emptySent(){
        testCoroutineRule.runBlockingTest {
            `when`(useCase.execute(PAGE_NUM)).thenReturn(getEmptyMoviesListOtherPage())
            moviesViewModel.fetchMovies(PAGE_NUM)
            assertThat(moviesViewModel.moviesLiveData.value, `is`(nullValue()))
            assertThat(moviesViewModel.emptyLiveData.value, `is`(PAGE_NUM))
            assertThat(moviesViewModel.failureLiveData?.value, `is`(nullValue()))
        }
    }

    @Test
    fun fetchMovies_notFirstFetchFailure_failureSent(){
        testCoroutineRule.runBlockingTest {
            `when`(useCase.execute(PAGE_NUM)).thenThrow(getException())
            moviesViewModel.fetchMovies(PAGE_NUM)
            assertThat(moviesViewModel.moviesLiveData.value, `is`(nullValue()))
            assertThat(moviesViewModel.emptyLiveData.value, `is`(nullValue()))
            assertThat(moviesViewModel.failureLiveData?.value, `is`(notNullValue()))
        }
    }

    private fun getFilledMoviesList(): MoviesResponse {
        val movies = listOf("first movie", "second movie")
        return MoviesResponse(1, movies)
    }
    
    private fun getFilledMoviesListOtherPage(): MoviesResponse {
        val movies = listOf("third movie", "fourth movie")
        return MoviesResponse(PAGE_NUM, movies)
    }

    private fun getEmptyMoviesList(): MoviesResponse {
        return MoviesResponse(1, listOf())
    }

    private fun getEmptyMoviesListOtherPage(): MoviesResponse {
        return MoviesResponse(PAGE_NUM, listOf())
    }

    private fun getException(): Exception {
        return RuntimeException("exception")
    }
    
    companion object{
        const val PAGE_NUM = 2
    }
}