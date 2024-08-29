package com.example.homeworkday11

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HomeAdapter(
    private var items: List<Item>,
    private var onClick: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView? = itemView.findViewById(R.id.textView)
        val likeView: View? = itemView.findViewById(R.id.like)
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView? = itemView.findViewById(R.id.imageView)
        val likeView: View? = itemView.findViewById(R.id.like)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TEXT_TYPE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
                TextViewHolder(view)
            }

            IMAGE_TYPE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
                ImageViewHolder(view)
            }

            else -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
                TextViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onClick(position)
        }
        when (val item = items[position]) {
            is Item.TextItem -> {
                val textHolder = holder as TextViewHolder
                textHolder.textView?.text = item.text
                textHolder.likeView?.run {
                    isSelected = item.like
                    setOnClickListener {
                        item.like = !item.like
                        isSelected = item.like
                    }
                }
            }

            is Item.ImageItem -> {
                val imageHolder = holder as ImageViewHolder
                imageHolder.imageView?.let {
                    Glide.with(holder.itemView)
                        .load(item.imageUrl)
                        .placeholder(R.drawable.placeholder) // 设置加载中的占位图
                        .error(R.drawable.error) // 设置加载失败的占位图
                        .into(it)
                }

                imageHolder.likeView?.run {
                    isSelected = item.like
                    setOnClickListener {
                        item.like = !item.like
                        isSelected = item.like
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is Item.TextItem -> TEXT_TYPE
        is Item.ImageItem -> IMAGE_TYPE
    }

    companion object {
        private const val TEXT_TYPE = 0
        private const val IMAGE_TYPE = 1
    }
}