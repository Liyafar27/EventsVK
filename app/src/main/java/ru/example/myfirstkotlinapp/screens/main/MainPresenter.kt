package ru.example.myfirstkotlinapp.screens.main

import com.omegar.mvp.InjectViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.example.myfirstkotlinapp.data.storage.Storage
import ru.example.myfirstkotlinapp.screens.base.BasePresenter

private const val PER_PAGE: Int = 100

@InjectViewState
class MainPresenter : BasePresenter<MainView>() {
    val storage = Storage.instance


  private   var page: Int = 1
        set(value) {
            field = value
            viewState.setPageNumber(field)
            updateRepo()
        }

      private    var repoName: String = ""
        set(value) {
            field = value
            viewState.setRepoName(repoName)
            updateRepo()
        }

    init {
        viewState.setPageNumber(page)
        viewState.setRepoName(repoName)
    }

    fun requestShowRepo(repoName: String) {
        this.repoName = repoName
    }



    private fun updateRepo() {
        GlobalScope.launch(Dispatchers.Main) {

            viewState.setList(storage.getStorageRepo(repoName, page, PER_PAGE))
//            viewState.setLimit(storage.getLimitRemaining())

        }

    }

    fun requestChangePage(diff: Int) {
        page += diff
    }
}
