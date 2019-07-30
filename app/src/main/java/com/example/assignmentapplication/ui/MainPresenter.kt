package com.example.assignmentapplication.ui

import com.example.assignmentapplication.ApiProvider
import com.example.assignmentapplication.ui.Model.DataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainPresenter(apiProvider: ApiProvider): MainContract.Presenter<MainContract.View>{

    val job = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + job)

    private var view:MainContract.View? = null

    private val repository : DataRepository = DataRepository(apiProvider.getApi())

    override fun attachView(view: MainContract.View){
        this.view = view
    }

    override fun loadNewPage(int:Int){
        uiScope.launch {
            val result = repository.getData(int)?.hits
            if(!result.isNullOrEmpty())
                view?.updateData(result)
        }
    }

    override fun loadData(){
        uiScope.launch {
            val result = repository.getData(1)?.hits
            if(!result.isNullOrEmpty())
                view?.setNewData(result)
        }
    }

    override fun detachView(){
        view = null
    }

}