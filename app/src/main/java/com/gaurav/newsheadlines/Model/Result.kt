package com.gaurav.newsheadlines.Model



data class Result (
    val status: String,
    val totalResults: Int,
    val articles : List<News>
        )