package com.example.assignmentapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentapplication.ApiProvider
import com.example.assignmentapplication.R
import com.example.assignmentapplication.ui.Model.Hit
import com.example.assignmentapplication.ui.recycler.ItemAdapter
import com.example.assignmentapplication.ui.recycler.PagingListener
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment: Fragment(), MainContract.View, ItemAdapter.OnDisplayItemCountListener {

    private lateinit var presenter: MainPresenter
    private lateinit var adapter: ItemAdapter
    private lateinit var itemsList: RecyclerView
    private var pagingListener = ScrollListener()

    override fun setNewData(list: List<Hit>) {
        adapter.setData(list)
    }

    override fun updateData(list: List<Hit>) {
        adapter.addData(list)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  = inflater.inflate(R.layout.main_fragment, container, false)

        itemsList = view.findViewById(R.id.recycler)

        itemsList.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ItemAdapter()
        adapter.addOnDisplayedItemCountListener(this)
        itemsList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        itemsList.adapter = this@MainFragment.adapter
        pagingListener.attach(itemsList)
        presenter = MainPresenter(context?.applicationContext as ApiProvider)
        presenter.attachView(this)
        presenter.loadData()
    }

    override fun onDisplayedItemCountChanged(count: Int) {
        tvCount.text = count.toString()
    }

    private inner class ScrollListener : PagingListener() {
        var page: Int = 1

        override fun lastItemVisible() {
            page++
            pagingListener.setPaused(true)
            presenter.loadNewPage(page)
        }
    }

}