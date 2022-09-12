package com.example.enews.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.enews.databinding.FragmentWebViewBinding

class webView : Fragment() {

    companion object {
        fun newInstance() = webView()
    }

    private var _binding: FragmentWebViewBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: WebViewViewModel

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
        val url = arguments?.getString("url")
        binding.webView.loadUrl(url!!)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()
    }


}