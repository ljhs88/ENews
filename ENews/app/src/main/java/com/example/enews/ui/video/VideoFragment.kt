package com.example.enews.ui.video

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enews.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {

    companion object{
    }

    private lateinit var _binding : FragmentVideoBinding
    private val binding get() = _binding

    private lateinit var viewModel : VideoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[VideoViewModel::class.java]

        val recyclerView = binding.recyclerview
        val manager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false)
        viewModel.data.observe(viewLifecycleOwner) {
            val list = viewModel.data.value?.videoList
            val adapter = context?.let { VideoAdapter(list!!, it, findNavController()) }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = manager
            Log.d("123", "recyclerView")
        }
        return binding.root
    }
}