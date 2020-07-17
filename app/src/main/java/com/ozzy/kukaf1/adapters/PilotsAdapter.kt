package com.ozzy.kukaf1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.ozzy.kukaf1.R
import com.ozzy.kukaf1.database.AppDatabase
import com.ozzy.kukaf1.interfaces.RecyclerViewClickListener
import com.ozzy.kukaf1.models.Pilot
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pilots_item.view.*

class PilotsAdapter(val pilots : List<Pilot>, itemListener: RecyclerViewClickListener, val context : Context) : RecyclerView.Adapter<PilotsAdapter.PilotsViewHolder>() {

    var itemListener: RecyclerViewClickListener

    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "dao"
    ).allowMainThreadQueries().build()

    init {
        this.itemListener = itemListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PilotsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.pilots_item,parent,false)
        return PilotsViewHolder(v)

    }

    override fun getItemCount(): Int {
        return pilots.size
    }


    override fun onBindViewHolder(holder: PilotsViewHolder, position: Int) {
        val pilot = pilots[position]
        holder.itemView.pilotName.text = pilot.name
        holder.itemView.pilotScore.text = pilot.point.toString()
        holder.itemView.setOnClickListener {
            itemListener.recyclerViewListClicked(holder.itemView, position)
        }
        var favList = db.dao().getAll()
        var isFav : Boolean = false
        for (fav in favList){
            if (pilot.id.toString().equals(fav.id.toString())){
                isFav = true
                break
            }
        }
        if (isFav){
            holder.itemView.pilotFav.text = "Remove From Favs"
            holder.itemView.pilotFav.setOnClickListener {
                db.dao().deleteById(pilot.id.toString())
                holder.itemView.pilotFav.text = "Add To Favs"
            }
        }else{
            holder.itemView.pilotFav.text = "Add To Favs"
            holder.itemView.pilotFav.setOnClickListener {
                db.dao().insert(pilot)
                holder.itemView.pilotFav.text = "Remove From Favs"
            }
        }
    }


    class PilotsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}