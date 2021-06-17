package ru.example.myfirstkotlinapp.screens.eventInfo

import com.omegar.mvp.InjectViewState
import ru.example.myfirstkotlinapp.screens.base.BasePresenter
import javax.inject.Inject


    @InjectViewState
    class  EventInfoPresenter
    @Inject constructor(private var nameIntent: String,
                        private var startDateIntent: String,
                        private var endDateIntent: String,
                        private var imageUrlIntent: String?,
                        private var descriptionIntent: String

    ) : BasePresenter<EventInfoView>() {

        fun showStarsList() {
            viewState.showStarsListForMonth(nameIntent, startDateIntent,endDateIntent,imageUrlIntent,descriptionIntent)
        }
    }
