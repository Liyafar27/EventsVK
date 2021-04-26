package ru.example.myfirstkotlinapp.screens.main


import com.omegar.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.omegar.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.omegar.mvp.viewstate.strategy.StateStrategyType
import ru.example.myfirstkotlinapp.data.remote.Limit
import ru.example.myfirstkotlinapp.model.GitHubRepo
import ru.example.myfirstkotlinapp.screens.base.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView: BaseView {
    fun setPageNumber(page: Int)
    fun setRepoName(repoName: String)
    fun personItemClicked(person: GitHubRepo)

    fun setList(list: List<GitHubRepo>)
//    fun setLimit(limit: Limit)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(message: String)

}


