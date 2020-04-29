package com.example.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

public class FirstFragment : Fragment(), NewsListAdapter.ItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var storiesRecyclerView: RecyclerView

    companion object {
        lateinit var adapter: NewsListAdapter
        private var bundleRecyclerViewState: Bundle? = null
    }

    private var storiesAdapter: StoriesAdapter? = null
    private val KEY_RECYCLER_STATE = "recycler_state"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater?.inflate(R.layout.first_fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        storiesRecyclerView = view.findViewById(R.id.recyclerViewStories)
        recyclerView.layoutManager =
            LinearLayoutManager(this.activity)
        storiesRecyclerView.layoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
        adapter =
            NewsListAdapter(
                listener = this
            )
        adapter.newsList = newsGenerator()

        recyclerView.setAdapter(adapter)
        storiesAdapter =
            StoriesAdapter(
                storyGenerator()
            )
        storiesRecyclerView.adapter = storiesAdapter
        return view
    }

    override fun onPause() {
        super.onPause()
        bundleRecyclerViewState = Bundle()
        val listState = recyclerView.layoutManager!!.onSaveInstanceState()
        if (bundleRecyclerViewState != null) {
            bundleRecyclerViewState!!.putParcelable(KEY_RECYCLER_STATE, listState)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.newsList = newsGenerator()
        adapter.notifyDataSetChanged()
        if (bundleRecyclerViewState != null) {
            val listState =
                bundleRecyclerViewState!!.getParcelable<Parcelable>(
                    KEY_RECYCLER_STATE
                )
            recyclerView.layoutManager!!.onRestoreInstanceState(listState)
        }
    }

    private fun newsGenerator(): List<News> {
        val list: MutableList<News> = ArrayList()
        val news = News(
            DBUtil.items.size,
            R.drawable.a2,
            R.drawable.p1,
            "Damir Moldabayev",
            "yesterday at 7:49",
            "asd jlaskdj lakd lkasjdl kjsdlfk jlsdk jldskf jlsdkjf lksdjf lksdl kf",
            false,
            545,
            123,
            2200
        )
        list?.add(news)
        return DBUtil.items
    }

    private fun storyGenerator(): ArrayList<Story> {
        var listStories = Stories.stories
        listStories.add(
            Story(
                1,
                "Премьеры",
                R.drawable.a2
            )
        )
        listStories.add(
            Story(
                2,
                "В Топе",
                R.drawable.a3
            )
        )
        listStories.add(
            Story(
                3,
                "Лучшее",
                R.drawable.a4
            )
        )
        listStories.add(
            Story(
                4,
                "Недавние",
                R.drawable.a10
            )
        )
        listStories.add(
            Story(
                5,
                "Всех Времен",
                R.drawable.a4
            )
        )
        listStories.add(
            Story(
                6,
                "Премьеры",
                R.drawable.a2
            )
        )
        listStories.add(
            Story(
                7,
                "В Топе",
                R.drawable.a3
            )
        )
        listStories.add(
            Story(
                8,
                "Лучшее",
                R.drawable.a4
            )
        )
        listStories.add(
            Story(
                9,
                "Недавние",
                R.drawable.a10
            )
        )
        listStories.add(
            Story(
                10,
                "Всех Времен",
                R.drawable.a11
            )
        )
        return listStories
    }


    override fun itemClick(position: Int, item: News?) {
        val intent = Intent(this.activity, NewsDetailActivity::class.java)
        intent.putExtra("news", item)
        startActivity(intent)
    }
}
