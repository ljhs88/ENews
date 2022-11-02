package com.example.enews.ui.first.etm

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.enews.R
import com.example.enews.bean.entertainment.T1348648517839
import com.example.enews.databinding.FragmentEntertainmentBinding
import com.example.enews.util.ToastUtil
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator

class entertainment() : Fragment() {

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
        _binding = FragmentEntertainmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(EntertainmentViewModel::class.java)
        viewModel.getContent()
        viewModel.data.observe(viewLifecycleOwner) {
            val recyclerList = viewModel.data.value?.T1348648517839
            val bannerList = viewModel.data.value?.T1348648517839?.subList(0, 5)
            setBanner(bannerList)
            setRecyclerView(recyclerList)
        }
        return binding.root
    }

    private fun setRecyclerView(list: List<T1348648517839>?) {
        val list1 = list?.subList(1, list.size)
        val recyclerView = binding.recyclerview
        val manager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false)
        val adapter = context?.let { it1 -> etmAdapter(list1 as List<T1348648517839>,
            it1,
            findNavController())
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager
    }

    private fun setBanner(list: List<T1348648517839>?) {
        binding.banner.setAdapter(object : BannerImageAdapter<T1348648517839>(list){
            override fun onBindView(
                holder: BannerImageHolder?,
                data: T1348648517839?,
                position: Int,
                size: Int,
            ) {
                if (holder != null&&data!=null) {
                    Glide.with(holder.itemView)
                        .load(data.imgsrc)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(5)))
                        .into(holder.imageView)
                    holder.imageView.setOnClickListener {
                        val url = data.url
                        if (url != null && url != "") {
                            val bundle = bundleOf("url" to data.url)
                            findNavController().navigate(R.id.action_video_to_webView, bundle)
                        } else {
                            ToastUtil.showMsg(context!!, "无详细文章，敬请期待")
                        }
                    }
                }
            }

        }).addBannerLifecycleObserver(this).indicator = CircleIndicator(context)
    }


}