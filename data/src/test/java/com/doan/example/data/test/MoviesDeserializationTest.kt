package com.doan.example.data.test

import com.doan.example.data.remote.models.responses.MoviesResponse
import com.doan.example.data.test.MockUtil.getModel
import junit.framework.TestCase.assertTrue
import org.junit.Test

class MoviesDeserializationTest {

    @Test
    fun `When parsing movies json sample string, it returns the data correctly`() {
        val result = getModel(MoviesResponse::class.java, "/movies-response.json")

        assertTrue(result.page == 1)
        assertTrue(result.totalPages == 43042)
        assertTrue(result.totalResults == 860840)
        assertTrue(result.results?.isNotEmpty() == true)
        val movie = result.results?.first()!!
        assertTrue(movie.adult == false)
        assertTrue(movie.backdropPath == "/deLWkOLZmBNkm8p16igfapQyqeq.jpg")
        assertTrue(movie.id == 763215L)
        assertTrue(movie.originalLanguage == "en")
        assertTrue(movie.originalTitle == "Damsel")
        assertTrue(movie.overview == "A young woman's marriage to a charming prince turns into a fierce fight for survival when she's offered up as a sacrifice to a fire-breathing dragon.")
        assertTrue(movie.popularity == 2754.204)
        assertTrue(movie.posterPath == "/sMp34cNKjIb18UBOCoAv4DpCxwY.jpg")
        assertTrue(movie.releaseDate == "2024-03-08")
        assertTrue(movie.title == "Damsel")
        assertTrue(movie.video == false)
        assertTrue(movie.voteAverage == 7.294f)
        assertTrue(movie.voteCount == 757L)
    }
}
