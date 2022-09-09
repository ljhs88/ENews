package com.example.enews.ui.first

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.enews.R
import com.example.enews.ui.first.model.MilitaryScienceViewModel

class militaryScience : Fragment() {

    companion object {
        fun newInstance() = militaryScience()
    }

    private lateinit var viewModel: MilitaryScienceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_military_science, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MilitaryScienceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}