package com.example.assignmentapplication.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assignmentapplication.R
import com.example.assignmentapplication.ui.Model.Hit
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class ItemAdapter  : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var data: ArrayList<Hit>? = null
    private val listeners = LinkedList<OnDisplayItemCountListener>()

    fun addOnDisplayedItemCountListener(listener: OnDisplayItemCountListener) {
        listeners.add(listener)
    }

    fun setData(data: List<Hit>) {
        this.data = ArrayList(data)
        notifyDataSetChanged()
    }
    fun addData(data: List<Hit>) {
        this.data?.addAll(data)
        notifyDataSetChanged()
        listeners.forEach { listener -> listener.onDisplayedItemCountChanged(data.size)}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(data?.get(position))
    }

    interface OnDisplayItemCountListener {
        fun onDisplayedItemCountChanged (count: Int)
    }

    inner class ItemViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        fun bindView(model: Hit?) {
            if (model == null) return
            v.tvTitle.text = model.title
            v.tvCreatedAt.text = model.created_at
        }
    }
}