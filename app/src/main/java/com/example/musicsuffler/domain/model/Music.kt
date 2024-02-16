package com.example.musicsuffler.domain.model

data class Music(
    val `data`: List<Data>,
    val next: String,
    val total: Int
)