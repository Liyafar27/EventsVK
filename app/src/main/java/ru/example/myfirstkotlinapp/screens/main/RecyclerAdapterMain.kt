package ru.example.myfirstkotlinapp.screens.main

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.example.myfirstkotlinapp.R
import ru.example.myfirstkotlinapp.data.remote.RemoteItemEvent
import java.text.SimpleDateFormat
import java.util.*


class RecyclerAdapterMain(
    private var values: List<RemoteItemEvent>,
    private val clickListener: (RemoteItemEvent) -> Unit
) : RecyclerView.Adapter<RecyclerAdapterMain.MyViewHolder>() {
    var finishDate: String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_repo, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val item = values[position]
        val urlImage = Uri.parse(item.photo_200)
        val position1 = position + 1
        val name = item.name

        if (item.finish_date !== 0L) {
            finishDate = " по "+ sdf.format(Date(item.finish_date * 1000))
        } else {
            finishDate = ""
        }
        val textName = "$position1.  $name"
        val textDate = "c " + sdf.format(Date(item.start_date * 1000)) +  finishDate


        holder.textName.setText(textName)
        holder.dateText.setText(textDate)
        holder.bindValue(values[position], clickListener)

        Glide.with(holder.itemView.context)
            .load(urlImage)
            .into(holder.image)

        setFadeAnimation(holder.itemView)
    }

    override fun getItemCount() = values.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textName: TextView = itemView.findViewById(R.id.textName)
        val dateText: TextView = itemView.findViewById(R.id.dateTextRepo)
        val image: ImageView = itemView.findViewById(R.id.imageView2)

        fun bindValue(itemModel: RemoteItemEvent, listener: (RemoteItemEvent) -> Unit) {

            itemView.setOnClickListener { listener(itemModel) }
        }
    }

    private fun setFadeAnimation(view: View) {

        val anim = AlphaAnimation(0.3f, 1.0f)
        anim.duration = 300
        view.startAnimation(anim)
    }

}








