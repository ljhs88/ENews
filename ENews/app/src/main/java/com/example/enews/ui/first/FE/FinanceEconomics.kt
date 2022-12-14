package com.example.enews.ui.first.FE

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
import com.example.enews.bean.FE.BA8EE5GMwangning
import com.example.enews.bean.entertainment.T1348648517839
import com.example.enews.databinding.FragmentEntertainmentBinding
import com.example.enews.ui.first.etm.etmAdapter
import com.example.enews.util.ToastUtil
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
import kotlin.concurrent.thread

class FinanceEconomics : Fragment() {

    companion object {
        fun newInstance() = FinanceEconomics()
    }

    private var _binding : FragmentEntertainmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FinanceEconomicsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEntertainmentBinding.inflate(
            inflater, container, false)
        viewModel = ViewModelProvider(this).get(FinanceEconomicsViewModel::class.java)
        thread {
            viewModel.getContent()
        }
        viewModel.data.observe(viewLifecycleOwner){
            val recyclerList = viewModel.data.value?.BA8EE5GMwangning
            val bannerList = viewModel.data.value?.BA8EE5GMwangning?.subList(0 ,5)
            setRecyclerView(recyclerList)
            setBanner(bannerList)
        }

        return binding.root
    }

    private fun setRecyclerView(list: List<BA8EE5GMwangning>?) {
        val list1 = list?.subList(1, list.size)
        val recyclerView = binding.recyclerview
        val manager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false)
        val adapter = context?.let { it1 -> FEAdapter(list1 as List<BA8EE5GMwangning>,
            it1,
            findNavController())
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager
    }

    private fun setBanner(list: List<BA8EE5GMwangning>?) {
        binding.banner.setAdapter(object : BannerImageAdapter<BA8EE5GMwangning>(list){
            override fun onBindView(
                holder: BannerImageHolder?,
                data: BA8EE5GMwangning?,
                position: Int,
                size: Int,
            ) {
                if (holder != null&&data!=null) {
                    Glide.with(holder.itemView)
                        .load(data.imgsrc)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                        .into(holder.imageView)
                    holder.imageView.setOnClickListener {
                        val url = data.url
                        if (url != null && url != "") {
                            val bundle = bundleOf("url" to data.url)
                            findNavController().navigate(R.id.action_video_to_webView, bundle)
                        } else {
                            ToastUtil.showMsg(context!!, "??????????????????????????????")
                        }
                    }
                }
            }

        }).addBannerLifecycleObserver(this).indicator = CircleIndicator(context)
    }

}