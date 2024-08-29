package com.example.homeworkday11

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.homeworkday11.ItemDataManager.items
import org.greenrobot.eventbus.EventBus

class DetailActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    private lateinit var likeView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val position = intent.getIntExtra(EXTRA_POSITION, -1)
        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)
        likeView = findViewById(R.id.like)

        when (val item = items[position]) {
            is Item.TextItem -> {
                textView.text = item.text
                likeView.run {
                    isSelected = item.like
                    setOnClickListener {
                        item.like = !item.like
                        isSelected = item.like
                        EventBus.getDefault().postSticky(MessageEvent(position, item.like))
                    }
                }
                textView.visibility = View.VISIBLE
                imageView.visibility = View.GONE
            }

            is Item.ImageItem -> {
                Glide.with(this)
                    .load(item.imageUrl)
                    .placeholder(R.drawable.placeholder) // 设置加载中的占位图
                    .error(R.drawable.error) // 设置加载失败的占位图
                    .into(imageView)

                likeView.run {
                    isSelected = item.like
                    setOnClickListener {
                        item.like = !item.like
                        isSelected = item.like
                        EventBus.getDefault().postSticky(MessageEvent(position, item.like))
                    }
                }
                textView.visibility = View.GONE
                imageView.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        const val EXTRA_POSITION = "position"
    }
}