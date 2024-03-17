package com.doan.example.data.test

import com.doan.example.data.remote.models.responses.MovieDetailResponse
import com.doan.example.data.test.MockUtil.getModel
import junit.framework.TestCase.assertTrue
import org.junit.Test

class MovieDetailDeserializationTest {

    @Test
    fun `When parsing movie detail json sample string, it returns the data correctly`() {
        val result = getModel(MovieDetailResponse::class.java, "/movie-detail-response.json")

        assertTrue(result.adult == false)
        assertTrue(result.backdropPath == "/deLWkOLZmBNkm8p16igfapQyqeq.jpg")
        assertTrue(result.id == 763215L)
        assertTrue(result.originalLanguage == "en")
        assertTrue(result.originalTitle == "Damsel")
        assertTrue(result.overview == "A young woman's marriage to a charming prince turns into a fierce fight for survival when she's offered up as a sacrifice to a fire-breathing dragon.")
        assertTrue(result.popularity == 2754.204)
        assertTrue(result.posterPath == "/sMp34cNKjIb18UBOCoAv4DpCxwY.jpg")
        assertTrue(result.releaseDate == "2024-03-08")
        assertTrue(result.title == "Damsel")
        assertTrue(result.video == false)
        assertTrue(result.voteAverage == 7.309f)
        assertTrue(result.voteCount == 765L)
        assertTrue(result.budget == 60000000.0)
        assertTrue(result.status == "Released")
    }
}
