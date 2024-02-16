package com.example.musicsuffler

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicsuffler.data.repository.ApiRepo
import com.example.musicsuffler.domain.model.Music
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainVM @Inject constructor(
    private val apiRepo: ApiRepo
) : ViewModel() {

    private val _music: MutableStateFlow<Music?> = MutableStateFlow(Music(emptyList(), "", 0))
    val music = _music.map { it }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        Music(emptyList(), "", 0)
    )

    init {
        getMusics("eminem")
    }

    private fun getMusics(q: String) {
        apiRepo.getMusics(q).enqueue(object : Callback<Music?> {
            override fun onResponse(call: Call<Music?>, response: Response<Music?>) {
                _music.value = response.body()
                Log.i("apiListTest", "onResponse: ${response.body()?.data}")
            }

            override fun onFailure(call: Call<Music?>, t: Throwable) {
                // api call failed.
                Log.i("apiListTest", "onFailure: $t")
            }
        })
    }

}