package com.rahul.aarati

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_play_audio.*

class PlayAudio : AppCompatActivity() {
    var mediaPlayer=MediaPlayer.create(this,R.raw.file)
    val handler=Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_audio)
        btnPlay
       // val handler=Handler()
        mediaPlayer.setOnPreparedListener(MediaPlayer.OnPreparedListener {
            seekbar.max=mediaPlayer.duration
            mediaPlayer.start()
            changeSeekbar()
        })

    }

    private fun changeSeekbar() {
        seekbar.progress=mediaPlayer.currentPosition
        if (mediaPlayer.isPlaying){
            val runnable= Runnable {
                changeSeekbar()
            }
            handler.postDelayed(runnable,1000)
        }
    }
}
