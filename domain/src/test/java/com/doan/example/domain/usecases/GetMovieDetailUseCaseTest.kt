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
class GetMovieDetailUseCaseTest {

    private lateinit var mockMovieRepository: MovieRepository
    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

    @Before
    fun setUp() {
        mockMovieRepository = mockk()
        getMovieDetailUseCase = GetMovieDetailUseCase(mockMovieRepository)
    }

    @Test
    fun `When request successful, it returns success`() = runTest {
        val expected = MockUtil.movieDetail
        every { mockMovieRepository.getMovieDetail(any()) } returns flowOf(expected)

        getMovieDetailUseCase(1).collect {
            it shouldBe expected
        }
    }

    @Test
    fun `When request failed, it returns error`() = runTest {
        val expected = Exception()
        every { mockMovieRepository.getMovieDetail(any()) } returns flow { throw expected }

        getMovieDetailUseCase(1).catch {
            it shouldBe expected
        }.collect()
    }
}
