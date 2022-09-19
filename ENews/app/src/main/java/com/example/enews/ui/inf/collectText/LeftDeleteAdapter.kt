package com.example.enews.ui.inf.collectText

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.enews.R
import com.example.enews.bean.inf.CollectTextBean
import com.example.enews.bean.inf.CollectTextBean2
import com.example.enews.databinding.ItemCollectBinding
import com.example.enews.util.ToastUtil
import com.google.gson.Gson

class LeftDeleteAdapter(
    private var list: List<CollectTextBean2>,
    private var context: Context,
    private var navController: NavController
) : RecyclerView.Adapter<LeftDeleteAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemCollectBinding) : RecyclerView.ViewHolder(binding.root){
        val title = binding.title
        val author = binding.author
        val image = binding.image
        val cardView = binding.cardView
        val delete = binding.delete
        val constraintLayout = binding.constraint
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCollectBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = list[position]
        val title = bean.title
        val author = bean.author
        val image = bean.image
        holder.title.text = title
        holder.author.text = author
        Glide.with(context)
            .load(image)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(5)))
            .into(holder.image)
        val url = bean.url
        holder.cardView.setOnClickListener {
            if (url != null && url != "") {
                val collectBean = CollectTextBean(title, image, author, url)
                val collectString = Gson().toJson(collectBean)
                val bundle = bundleOf("bean" to collectString)
                navController.navigate(R.id.action_collectTextFragment_to_webView, bundle)
            } else {
                ToastUtil.showMsg(context, "无详细文章，敬请期待")
            }
        }
    }

    override fun getItemCount() = list.size
}