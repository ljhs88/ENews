package com.example.enews.ui.video

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.enews.R
import com.example.enews.bean.inf.CollectTextBean
import com.example.enews.bean.video.VideoList
import com.example.enews.databinding.ItemVideoBinding
import com.google.gson.Gson

class VideoAdapter(
    private val data: List<VideoList>,
    private val context: Context,
    private val navController: NavController
) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root){
        val title = binding.title
        val author = binding.author
        val image = binding.image
        val cardView = binding.cardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVideoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = data[position]
        val imgUrl = bean.cover
        val title = bean.title
        val author = bean.videosource
        val url = bean.mp4_url
        Glide.with(context).load(imgUrl).into(holder.image)
        holder.title.text = title
        holder.author.text = author
        holder.cardView.setOnClickListener {
            val collectBean = CollectTextBean(title, imgUrl, author, url)
            val collectString = Gson().toJson(collectBean)
            val bundle = bundleOf("bean" to collectString.toString())
            navController.navigate(R.id.action_video_to_webView, bundle)
        }
    }

    override fun getItemCount() = data.size
}