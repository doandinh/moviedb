package com.doan.example.data.repositories

import com.doan.example.data.remote.models.responses.toMovies
import com.doan.example.data.remote.services.ApiService
import com.doan.example.data.test.MockUtil
import com.doan.example.domain.repositories.Repository
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RepositoryTest {

    private lateinit var mockService: ApiService
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        mockService = mockk()
        repository = RepositoryImpl(mockService)
    }

    @Test
    fun `When request successful, it returns success`() = runTest {
        val expected = MockUtil.movieResponse
        coEvery { mockService.getMovies() } returns expected

        repository.getMovies().collect {
            it shouldBe expected.toMovies()
        }
    }

    @Test
    fun `When request failed, it returns error`() = runTest {
        val expected = Throwable()
        coEvery { mockService.getMovies() } throws expected

        repository.getMovies().catch {
            it shouldBe expected
        }.collect()
    }
}
