package com.example.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

public class SecondFragment : Fragment(), FavoriteNewsListAdapter.ItemClickListener {
    private lateinit var favRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        var favoriteItems: MutableList<News> = mutableListOf()
        lateinit var favAdapter: FavoriteNewsListAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater?.inflate(R.layout.second_fragment, container, false)
        favRecyclerView = view.findViewById(R.id.favRecyclerView)
        favRecyclerView.layoutManager =
            LinearLayoutManager(this.activity)

        favAdapter =
            FavoriteNewsListAdapter(
                listener = this
            )
        favAdapter.favNewsList = favNewsGenerator()

        favRecyclerView.setAdapter(favAdapter)

        return view
    }

    override fun onResume() {
        super.onResume()
        favAdapter.favNewsList = favNewsGenerator()
        favAdapter.notifyDataSetChanged()
    }

    override fun itemClick(position: Int, item: News?) {
        val intent = Intent(this.activity, NewsDetailActivity::class.java)
        intent.putExtra("news", item)
        startActivity(intent)
    }

    fun favNewsGenerator(): MutableList<News> {
        return favoriteItems
    }
}