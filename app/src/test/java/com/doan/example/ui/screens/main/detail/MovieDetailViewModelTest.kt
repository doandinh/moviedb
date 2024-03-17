package com.doan.example.ui.screens.main.detail

import app.cash.turbine.test
import com.doan.example.domain.usecases.GetMovieDetailUseCase
import com.doan.example.domain.usecases.GetMoviesUseCase
import com.doan.example.model.toUiModel
import com.doan.example.test.CoroutineTestRule
import com.doan.example.test.MockUtil
import com.doan.example.ui.screens.home.HomeViewModel
import com.doan.example.ui.screens.moviedetail.MovieDetailViewModel
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
class MovieDetailViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    private val mockGetMovieDetailUseCase: GetMovieDetailUseCase = mockk()

    private lateinit var viewModel: MovieDetailViewModel

    @Before
    fun setUp() {
        every { mockGetMovieDetailUseCase(any()) } returns flowOf(MockUtil.movieDetail)

        initViewModel()
    }

    @Test
    fun `When getting movie detail successfully, it shows the corresponding data`() = runTest {
        viewModel.movieDetailUiModel.test {
            viewModel.getMovieDetail(1)
            expectMostRecentItem() shouldBe MockUtil.movieDetail.toUiModel()
        }
    }

    @Test
    fun `When loading movie detail failed, it shows the corresponding error`() = runTest {
        val error = Exception()
        every { mockGetMovieDetailUseCase(any()) } returns flow { throw error }
        viewModel.error.test {
            viewModel.getMovieDetail(1)
            expectMostRecentItem() shouldBe error
        }
    }

    @Test
    fun `When loading movie detail, it shows and hides loading correctly`() = runTest {
        coroutinesRule.testDispatcher = StandardTestDispatcher()
        viewModel.isLoading.test {
            viewModel.getMovieDetail(1)
            awaitItem() shouldBe true
        }
    }

    private fun initViewModel(dispatchers: DispatchersProvider = coroutinesRule.testDispatcherProvider) {
        viewModel = MovieDetailViewModel(
            dispatchers,
            mockGetMovieDetailUseCase
        )
    }
}
