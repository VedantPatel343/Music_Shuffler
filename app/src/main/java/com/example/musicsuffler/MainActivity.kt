package com.example.musicsuffler

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.musicsuffler.domain.model.Data
import com.example.musicsuffler.domain.model.Music
import com.example.musicsuffler.ui.theme.MusicSufflerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var vm: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicSufflerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                    val music by vm.music.collectAsStateWithLifecycle()

                    Greeting(music)
                }
            }
        }
    }
}

@Composable
fun Greeting(music: Music?) {

    val musics = music?.data
    val context = LocalContext.current

    if (musics != null) {
        LazyColumn {
            items(items = musics, key = { it.id }) {
                MusicItem(context, it)
            }
        }
    }

}

@Composable
fun MusicItem(context: Context, it: Data) {

    val mediaPlayer = MediaPlayer.create(context, it.preview.toUri())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(7.dp)
    ) {
        Column(Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    painter = rememberAsyncImagePainter(it.album.cover),
                    contentDescription = "image cover"
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = it.title)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { mediaPlayer.start() }) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow, contentDescription = "play music"
                    )
                }

                IconButton(onClick = { mediaPlayer.pause() }) {
                    Icon(imageVector = Icons.Default.Pause, contentDescription = "pause music")
                }
            }
        }
    }
}
