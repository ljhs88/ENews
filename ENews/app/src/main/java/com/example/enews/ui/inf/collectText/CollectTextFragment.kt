package com.example.enews.ui.inf.collectText

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enews.bean.inf.CollectTextBean
import com.example.enews.databinding.FragmentCollectTextBinding

class CollectTextFragment : Fragment(), LeftDeleteRecyclerView.OnItemClickListener{

    companion object;

    private lateinit var recyclerView: LeftDeleteRecyclerView
    private var _binding: FragmentCollectTextBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: collectTextViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectTextBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[collectTextViewModel::class.java]

        viewModel.data.observe(viewLifecycleOwner) {
            val list = viewModel.data.value
            setRecyclerView(list)
        }
        return binding.root
    }

    private fun setRecyclerView(list: List<CollectTextBean>?) {
        val list1 = list?.subList(1, list.size)
        recyclerView = binding.recyclerview
        recyclerView.setOnItemClickListener(this)
        val manager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false)
        val adapter = context?.let { it1 -> LeftDeleteAdapter(list1 as List<CollectTextBean>,
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

    override fun onClick(view: View?, position: Int) {
        recyclerView.adapter?.notifyItemRemoved(position)
    }


}