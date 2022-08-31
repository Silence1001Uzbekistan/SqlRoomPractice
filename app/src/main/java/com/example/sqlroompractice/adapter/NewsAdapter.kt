package com.example.sqlroompractice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlroompractice.databinding.ItemNewsBinding
import com.example.sqlroompractice.entity.News

class NewsAdapter(var list: ArrayList<News>, var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<NewsAdapter.Vh>() {

    inner class Vh(var itemNewsBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(itemNewsBinding.root) {

        fun onBind(news: News, position: Int) {

            itemNewsBinding.tv1.text = news.title
            itemNewsBinding.tv2.text = news.desc

            itemNewsBinding.deleteBtn.setOnClickListener {

                onItemClickListener.onItemDelete(news, position)

            }

            itemNewsBinding.editBtn.setOnClickListener {

                onItemClickListener.onItemEdit(news, position)

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {

        return Vh(ItemNewsBinding.inflate(LayoutInflater.from(parent.context)))

    }

    override fun onBindViewHolder(holder: Vh, position: Int) {

        holder.onBind(list[position], position)

    }

    override fun getItemCount(): Int {

        return list.size

    }

    interface OnItemClickListener {

        fun onItemDelete(news: News, position: Int)
        fun onItemEdit(news: News, position: Int)

    }

}