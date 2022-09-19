package com.example.enews.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.enews.R
import com.example.enews.bean.inf.CollectTextBean
import com.example.enews.bean.inf.CollectTextBean2
import com.example.enews.databinding.FragmentWebViewBinding
import com.example.enews.util.ShareUtil
import com.google.gson.Gson

class webView : Fragment() {

    companion object {
        fun newInstance() = webView()
    }

    private var _binding: FragmentWebViewBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: WebViewViewModel

    private lateinit var bean: CollectTextBean2
    // 0-未收藏 1-收藏
    private var COLLECT_STATE = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[WebViewViewModel::class.java]
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val collectJson = arguments?.getString("bean")
        bean = Gson().fromJson(collectJson, CollectTextBean2::class.java)
        binding.webView.loadUrl(bean.url)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()

        binding.collect.setOnClickListener {
            val picture : Int
            if(COLLECT_STATE == 0) {
                picture = R.drawable.collection2
                COLLECT_STATE = 1
            } else {
                picture =  R.drawable.collection
                COLLECT_STATE = 0
            }
            binding.collect.setBackgroundResource(picture)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (COLLECT_STATE == 1) {
            val list = ShareUtil.getDataList(requireContext(),"collectList")
                        as MutableList<CollectTextBean2>
            list.add(bean)
            ShareUtil.setDataList(requireContext(),"collectList", list)
        }
    }


}