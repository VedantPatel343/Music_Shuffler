package com.example.musicsuffler.remote

import com.example.musicsuffler.domain.model.Music
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("X-RapidAPI-Key: c15646a3c0msh7a6ea64128e6fa8p1880fdjsn58e7e22dfc6c",
            "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getMusics(@Query("q") query: String) : Call<Music>

}