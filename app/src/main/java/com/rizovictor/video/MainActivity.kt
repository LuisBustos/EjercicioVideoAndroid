package com.rizovictor.video

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.MediaController
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.rizovictor.video.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.text1.text = "Hola mundo"

        val mediacontroller = MediaController(this)
        mediacontroller.setAnchorView(binding.videoView)
        var contenido : Uri
        val arregloVideo: IntArray = intArrayOf(R.raw.video1, R.raw.video2, R.raw.video3)
        ///Se necesitan permisos para el internet.
        binding.videoView.setMediaController(mediacontroller)
        contenido = Uri.parse("android.resource://$packageName/${arregloVideo[0]}")
        binding.videoView.requestFocus()


        binding.btnPlay.setOnClickListener{
            binding.videoView.start()
        }

        binding.btnStop.setOnClickListener {
            binding.videoView.stopPlayback()
        }

        binding.btnSearch.setOnClickListener{
            val url:String = binding.textInputLayout.editText!!.text.toString()
            contenido = Uri.parse(url)
            Log.e("Error",contenido.toString())
            binding.videoView.setVideoURI(contenido)
            binding.videoView.start()
        }


        binding.videoView!!.setOnErrorListener(MediaPlayer.OnErrorListener {
                mediaPlayer, i, i2 ->
            Toast.makeText(this,"error", Toast.LENGTH_SHORT).show()
            true
        })

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                contenido = Uri.parse("android.resource://$packageName/${arregloVideo[position]}")
                binding.videoView.setVideoURI(contenido)
            }
        }
    }
}