package com.example.enews.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.enews.R

class VideoPlayerFragment : Fragment() {

    companion object {
        fun newInstance() = VideoPlayerFragment()
    }

    private lateinit var viewModel: VideoPlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this).get(VideoPlayerViewModel::class.java)
        return inflater.inflate(R.layout.fragment_video_player, container, false)
    }

}