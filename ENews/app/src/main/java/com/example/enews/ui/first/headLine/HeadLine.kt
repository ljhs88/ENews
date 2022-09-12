package com.example.enews.ui.first.headLine

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enews.bean.headLine.T1348647853363
import com.example.enews.databinding.FragmentHeadLineBinding

class headLine() : Fragment() {
    companion object {
        fun newInstance() = headLine()
    }
    private var _binding: FragmentHeadLineBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HeadLineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeadLineBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HeadLineViewModel::class.java)

        viewModel.data.observe(viewLifecycleOwner) {
            val items = viewModel.data.value?.T1348647853363
            val recyclerView = binding.recyclerview
            val manager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            val adapter = context?.let { MyAdapter(items as ArrayList<T1348647853363>,
                it,
                findNavController())
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = manager
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}