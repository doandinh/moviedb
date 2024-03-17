package com.doan.example.domain.usecases

import com.doan.example.domain.repositories.MovieRepository
import com.doan.example.domain.test.MockUtil
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMoviesUseCaseTest {

    private lateinit var mockMovieRepository: MovieRepository
    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @Before
    fun setUp() {
        mockMovieRepository = mockk()
        getMoviesUseCase = GetMoviesUseCase(mockMovieRepository)
    }

    @Test
    fun `When request successful, it returns success`() = runTest {
        val expected = MockUtil.movies
        every { mockMovieRepository.getMovies() } returns flowOf(expected)

        getMoviesUseCase().collect {
            it shouldBe expected
        }
    }

    @Test
    fun `When request failed, it returns error`() = runTest {
        val expected = Exception()
        every { mockMovieRepository.getMovies() } returns flow { throw expected }

        getMoviesUseCase().catch {
            it shouldBe expected
        }.collect()
    }
}
