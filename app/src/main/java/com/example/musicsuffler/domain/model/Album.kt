package com.example.musicsuffler.domain.model

data class Album(
    val id: Int,
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val md5_image: String,
    val title: String,
    val tracklist: String,
    val type: String
)