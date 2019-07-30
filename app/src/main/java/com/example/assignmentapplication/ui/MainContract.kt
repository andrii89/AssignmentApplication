package com.example.assignmentapplication.ui

import com.example.assignmentapplication.ui.Model.Hit

interface MainContract {
    interface View{
        fun setNewData(list: List<Hit>)
        fun updateData(list: List<Hit>)
    }

    interface Presenter<view: MainContract.View>{
        fun attachView(view: View)
        fun loadNewPage(int:Int)
        fun loadData()
        fun detachView()
    }
}