package com.doan.example.domain.usecases

import com.doan.example.domain.repositories.Repository
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
class UseCaseTest {

    private lateinit var mockRepository: Repository
    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @Before
    fun setUp() {
        mockRepository = mockk()
        getMoviesUseCase = GetMoviesUseCase(mockRepository)
    }

    @Test
    fun `When request successful, it returns success`() = runTest {
        val expected = MockUtil.movies
        every { mockRepository.getMovies() } returns flowOf(expected)

        getMoviesUseCase().collect {
            it shouldBe expected
        }
    }

    @Test
    fun `When request failed, it returns error`() = runTest {
        val expected = Exception()
        every { mockRepository.getMovies() } returns flow { throw expected }

        getMoviesUseCase().catch {
            it shouldBe expected
        }.collect()
    }
}
