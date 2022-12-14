package com.example.enews.ui.inf.collectText

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enews.bean.inf.CollectTextBean
import com.example.enews.bean.inf.CollectTextBean2
import com.example.enews.databinding.FragmentCollectTextBinding
import com.example.enews.util.ShareUtil
import com.example.enews.util.ShareUtil.getDataList
import com.example.enews.util.ShareUtil.setDataList

class CollectTextFragment : Fragment(), LeftDeleteRecyclerView.OnItemClickListener{

    companion object;

    private lateinit var recyclerView: LeftDeleteRecyclerView
    private var _binding: FragmentCollectTextBinding? = null
    private val binding get() = _binding!!

    private lateinit var list : MutableList<CollectTextBean2>

    private lateinit var viewModel: collectTextViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectTextBinding.inflate(inflater, container, false)
        //viewModel = ViewModelProvider(this)[collectTextViewModel::class.java]

        list = getDataList(requireContext(), "collectList") as MutableList<CollectTextBean2>
        if (list.isNotEmpty()) {
            binding.text.visibility = View.GONE
            setRecyclerView(list)
        } else {
            binding.recyclerview.visibility = View.GONE
        }
        return binding.root
    }

    private fun setRecyclerView(list: List<CollectTextBean2>?) {
        recyclerView = binding.recyclerview
        recyclerView.setOnItemClickListener(this)
        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = context?.let { it1 -> LeftDeleteAdapter(list as List<CollectTextBean2>,
            it1,
            findNavController())
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager
    }

    override fun onClick(view: View?, position: Int) {
        recyclerView.adapter?.notifyItemRemoved(position)
        list.removeAt(position)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        setDataList(requireContext(), "collectList", list)
    }
}