package com.doan.example.ui.screens.main.home

import app.cash.turbine.test
import com.doan.example.domain.usecases.GetMoviesUseCase
import com.doan.example.model.toUiModel
import com.doan.example.test.CoroutineTestRule
import com.doan.example.test.MockUtil
import com.doan.example.ui.base.NavigationEvent
import com.doan.example.ui.screens.home.HomeViewModel
import com.doan.example.util.DispatchersProvider
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.*

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    private val mockGetMoviesUseCase: GetMoviesUseCase = mockk()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        every { mockGetMoviesUseCase() } returns flowOf(MockUtil.movies)

        initViewModel()
    }

    @Test
    fun `When getting movies successfully, it shows the movie list`() = runTest {
        viewModel.movieUiModels.test {
            viewModel.getMovies()
            expectMostRecentItem() shouldBe MockUtil.movies.results.map { it.toUiModel() }
        }
    }

    @Test
    fun `When loading movies failed, it shows the corresponding error`() = runTest {
        val error = Exception()
        every { mockGetMoviesUseCase() } returns flow { throw error }
        viewModel.error.test {
            viewModel.getMovies()
            expectMostRecentItem() shouldBe error
        }
    }

    @Test
    fun `When loading movies, it shows and hides loading correctly`() = runTest {
        every { mockGetMoviesUseCase() } returns flowOf(MockUtil.movies)
        coroutinesRule.testDispatcher = StandardTestDispatcher()
        viewModel.isLoading.test {
            viewModel.getMovies()
            awaitItem() shouldBe true
        }
    }

    @Test
    fun `When navigating to movie detail, it should emit the correct data`() = runTest {
        viewModel.navigator.test {
            viewModel.navigateToDetail(1)
            awaitItem() shouldBe NavigationEvent.MovieDetail(1)
        }
    }

    private fun initViewModel(dispatchers: DispatchersProvider = coroutinesRule.testDispatcherProvider) {
        viewModel = HomeViewModel(
            dispatchers,
            mockGetMoviesUseCase
        )
    }
}
