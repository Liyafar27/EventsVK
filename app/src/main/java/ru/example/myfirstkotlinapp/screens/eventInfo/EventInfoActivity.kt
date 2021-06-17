package ru.example.myfirstkotlinapp.screens.eventInfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.omegar.mvp.presenter.InjectPresenter
import com.omegar.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_event_info.*
import ru.example.myfirstkotlinapp.R
import ru.example.myfirstkotlinapp.screens.base.BaseActivity

class EventInfoActivity : BaseActivity(), EventInfoView {

    @InjectPresenter
    lateinit var presenter: EventInfoPresenter


    companion object {
        private const val EXTRA_NAME = "nameIntent"
        private const val EXTRA_START_DAY = "startDateIntent"
        private const val EXTRA_END_DAY = "endDateIntent"
        private const val EXTRA_IMAGE_URL = "imageUrlIntent"
        private const val EXTRA_DESCRIPTION = "descriptionIntentIntent"


        fun createIntentEvent(context: Context, nameIntent: String, startDateIntent: String, endDateIntent: String, imageUrlIntent: String?, descriptionIntent: String): Intent {

            return Intent(context, EventInfoActivity::class.java)
                .putExtra(EXTRA_NAME, nameIntent )
                .putExtra(EXTRA_START_DAY, startDateIntent)
                .putExtra(EXTRA_END_DAY, endDateIntent)
                .putExtra(EXTRA_IMAGE_URL, imageUrlIntent)
                .putExtra(EXTRA_DESCRIPTION, descriptionIntent )


        }
    }

    @ProvidePresenter
    fun providePresenter(): EventInfoPresenter {
        val nameIntent = intent.getStringExtra(EXTRA_NAME)!!
        val startDateIntent = intent.getStringExtra(EXTRA_START_DAY)!!
        val endDateIntent = intent.getStringExtra(EXTRA_END_DAY)!!
        val imageUrlIntent = intent.getStringExtra(EXTRA_IMAGE_URL)
        val descriptionIntent = intent.getStringExtra(EXTRA_DESCRIPTION)!!
        return EventInfoPresenter(nameIntent, startDateIntent, endDateIntent, imageUrlIntent, descriptionIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_info)

        providePresenter()
        presenter.showStarsList()

    }

    override fun showStarsListForMonth(
        nameIntent: String,
        startDateIntent: String,
        endDateIntent: String,
        imageUrlIntent: String?,
        descriptionIntent: String
    ) {
        recyclerView.adapter = CustomRecyclerAdapter( nameIntent,
            startDateIntent,
            endDateIntent,
            imageUrlIntent,
            descriptionIntent)
    }
 
}
