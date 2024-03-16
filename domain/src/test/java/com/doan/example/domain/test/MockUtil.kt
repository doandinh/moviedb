package com.doan.example.domain.test

import com.doan.example.domain.models.Movies

object MockUtil {

    val movies = Movies(
        page = 1,
        results = listOf(),
        totalPages = 99,
        totalResults = 100
    )
}
