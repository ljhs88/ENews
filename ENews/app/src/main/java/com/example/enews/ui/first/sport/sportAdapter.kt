package com.example.enews.ui.first.sport

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.enews.R
import com.example.enews.bean.entertainment.T1348648517839
import com.example.enews.bean.inf.CollectTextBean
import com.example.enews.bean.sport.T1348649079062
import com.example.enews.databinding.ItemHeadLineBinding
import com.example.enews.util.ToastUtil
import com.google.gson.Gson

class sportAdapter(
    private var list: List<T1348649079062>,
    private var context: Context,
    private var navController: NavController
) : RecyclerView.Adapter<sportAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemHeadLineBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.image
        val title = binding.title
        val author = binding.author
        val cardView = binding.cardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHeadLineBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean = list[position]
        val imgUrl = bean.imgsrc
        val author = bean.source
        Glide.with(context)
            .load(imgUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(5)))
            .into(holder.image)
        val buffer = StringBuffer()
        buffer.apply {
            append(author)
            append("  ")
            append(bean.mtime.substring(5))
        }
        holder.title.text = bean.title
        holder.author.text = buffer.toString()
        holder.cardView.setOnClickListener {
            bean.apply {
                if (url != null && url != "") {
                    val collectBean = CollectTextBean(title, imgUrl, author, url)
                    val collectString = Gson().toJson(collectBean)
                    val bundle = bundleOf("bean" to collectString)
                    navController.navigate(R.id.action_video_to_webView, bundle)
                } else {
                    ToastUtil.showMsg(context, "??????????????????????????????")
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}