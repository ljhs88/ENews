package com.example.enews.ui.first.MS

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enews.R
import com.example.enews.bean.FE.BA8EE5GMwangning
import com.example.enews.bean.MS.BAI67OGGwangning
import com.example.enews.databinding.FragmentMilitaryScienceBinding
import com.example.enews.ui.first.FE.FEAdapter

class militaryScience : Fragment() {

    companion object {
        fun newInstance() = militaryScience()
    }

    private var _binding : FragmentMilitaryScienceBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MilitaryScienceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMilitaryScienceBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MilitaryScienceViewModel::class.java)

        viewModel.data.observe(viewLifecycleOwner) {
            val recyclerList = viewModel.data.value?.BAI67OGGwangning
            setRecyclerView(recyclerList)
        }

        return binding.root
    }

    private fun setRecyclerView(list: List<BAI67OGGwangning>?) {
        val list1 = list?.subList(1, list.size)
        val recyclerView = binding.recyclerview
        val manager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false)
        val adapter = context?.let { it1 -> MSAdapter(list1 as List<BAI67OGGwangning>,
            it1,
            findNavController())
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}