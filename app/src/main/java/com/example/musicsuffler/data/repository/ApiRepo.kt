package com.example.musicsuffler.data.repository

import com.example.musicsuffler.domain.model.Music
import com.example.musicsuffler.remote.ApiInterface
import retrofit2.Call
import javax.inject.Inject

class ApiRepo @Inject constructor(private val apiInterface: ApiInterface) {

    fun getMusics(query: String): Call<Music> {
        return apiInterface.getMusics(query)
    }

}