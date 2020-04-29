package com.example.fragments

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FavoriteNewsListAdapter(
    var favNewsList: MutableList<News>? = null,
    var listener: ItemClickListener?
) : RecyclerView.Adapter<FavoriteNewsListAdapter.FavoriteNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteNewsViewHolder {
        return FavoriteNewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteNewsViewHolder, position: Int) {
        val news = favNewsList?.get(position)
        Glide.with(holder.avatar.context)
            .load(news?.avaURL)
            .into(holder.avatar)
        Glide.with(holder.contentImg.context)
            .load(news?.contentImgURL)
            .into(holder.contentImg)

        if (news != null) {
            if (news.isLiked) {
                Glide.with(holder.imgLike.context)
                    .load(R.drawable.liked)
                    .into(holder.imgLike)
            } else {
                Glide.with(holder.imgLike.context)
                    .load(R.drawable.like)
                    .into(holder.imgLike)
            }
            holder.tvLike.text = news.likesNo.toString()
            holder.tvAuthor.text = news.author
            holder.tvDate.text = news.date
            holder.tvText.text = news.text
        }

        holder.itemView.setOnClickListener {
            if (news != null) {
                listener?.itemClick(position, news)
            }
        }

        holder.imgLike.setOnClickListener { v ->
            if (news != null) {
                for (item in DBUtil.items) {
                    if (news.id == item.id) {
                        item.isLiked = false
                        item.likesNo = item.likesNo - 1
                    }
                }
                news.isLiked = false
                favNewsList?.remove(news)
                notifyDataSetChanged()
                FirstFragment.adapter.notifyDataSetChanged()
                news.likesNo = news.likesNo + 1
                Glide.with(holder.imgLike.context)
                    .load(R.drawable.like)
                    .into(holder.imgLike)
                holder.tvLike.text = Integer.toString(news.likesNo)
                val toast = Toast.makeText(
                    v.context,
                    "Liked!",
                    Toast.LENGTH_SHORT
                )
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }

        }
    }

    inner class FavoriteNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val avatar: ImageView = itemView.findViewById(R.id.avatar)
        val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val tvText: TextView = itemView.findViewById(R.id.text)
        val contentImg: ImageView = itemView.findViewById(R.id.contentImg)
        val imgLike: ImageView = itemView.findViewById(R.id.imgLike)
        val tvLike: TextView = itemView.findViewById(R.id.tvLike)
    }

    override fun getItemCount(): Int = favNewsList?.size ?: 0

    interface ItemClickListener {
        fun itemClick(position: Int, item: News?)
    }
}