package com.example.enews.ui.first.etm

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.enews.R
import com.example.enews.databinding.FragmentEntertainmentBinding

class entertainment : Fragment() {

    companion object {
        fun newInstance() = entertainment()
    }

    private var _binding: FragmentEntertainmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: EntertainmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_entertainment, container, false)
        viewModel = ViewModelProvider(this).get(EntertainmentViewModel::class.java)
        return binding.root
    }

}