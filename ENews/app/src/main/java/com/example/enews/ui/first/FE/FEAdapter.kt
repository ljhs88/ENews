package com.example.enews.ui.first.FE

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.enews.R
import com.example.enews.bean.FE.BA8EE5GMwangning
import com.example.enews.databinding.ItemHeadLineBinding
import com.example.enews.util.ToastUtil

class FEAdapter(
    private var list: List<BA8EE5GMwangning>,
    private var context: Context,
    private var navController: NavController
) : RecyclerView.Adapter<FEAdapter.ViewHolder>() {

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
        Glide.with(context).load(imgUrl).into(holder.image)
        val buffer = StringBuffer()
        buffer.apply {
            append(bean.source)
            append("  ")
            append(bean.ptime.substring(5))
        }
        holder.title.text = bean.title
        holder.author.text = buffer.toString()
        holder.cardView.setOnClickListener {
            bean.apply {
                if (url != null && url != "") {
                    val bundle = bundleOf("url" to url)
                    navController.navigate(R.id.action_video_to_webView, bundle)
                } else {
                    ToastUtil.showMsg(context, "无详细文章，敬请期待")
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}