package ru.example.myfirstkotlinapp.screens.eventInfo

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.example.myfirstkotlinapp.R
import java.text.SimpleDateFormat
import java.util.*


class CustomRecyclerAdapter(
    private val nameIntent: String,
    private val startDateIntent: String,
    private val endDateIntent: String,
    private val imageUrlIntent: String?,
    private val descriptionIntent: String

) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {
    var finishDate: String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_info_event, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val endDateString = sdf.format(Date(endDateIntent.toLong() * 1000))

        if ( endDateString == "01/01/1970") {
            finishDate = ""
        } else {
            finishDate = " по " + endDateString
        }

        val message = "  c " + sdf.format(Date(startDateIntent.toLong() * 1000)) + finishDate
        holder.textName.text = nameIntent
        holder.textDate.setText(message)
        holder.textDescription.text = descriptionIntent
        val uri = Uri.parse(imageUrlIntent?.substringAfter("url=")?.substringBefore(")"))

        Glide.with(holder.itemView.context)
            .load(uri)
            .into(holder.imageView2)
        setFadeAnimation(holder.itemView)
    }

    override fun getItemCount() = 1

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textName: TextView = itemView.findViewById(R.id.textName)
        val imageView2: ImageView = itemView.findViewById(R.id.imageView2)
        val textDate: TextView = itemView.findViewById(R.id.dateText)
        val textDescription: TextView = itemView.findViewById(R.id.descriptionText)

    }
}

fun setFadeAnimation(view: View) {

    val anim = AlphaAnimation(0.0f, 1.0f)
    anim.duration = 500
    view.startAnimation(anim)
}

