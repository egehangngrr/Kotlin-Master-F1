package com.ozzy.kukaf1.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.room.Room

import com.ozzy.kukaf1.R
import com.ozzy.kukaf1.app.MyApplication
import com.ozzy.kukaf1.database.AppDatabase
import com.ozzy.kukaf1.models.Pilot
import com.ozzy.kukaf1.view_models.PilotsViewModel
import com.ozzy.kukaf1.view_models.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pilot_detail_fragment.*
import javax.inject.Inject

class FragmentPilotDetail : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var db : AppDatabase? = null

    private lateinit var viewModel: PilotsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pilot_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity!!.application as MyApplication).appComponent.doInjection(this)

        db = Room.databaseBuilder(
            context!!,
            AppDatabase::class.java, "dao"
        ).allowMainThreadQueries().build()

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PilotsViewModel::class.java)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PilotsViewModel::class.java)
        viewModel.driverDetailResponse().observe(this, Observer<Pilot> { this.consumeResponse(it) })
        viewModel.getDriverDetail(arguments!!.getInt("pilotId").toString())
    }


    private fun consumeResponse(pilot: Pilot?) {

        Picasso.get().load(pilot!!.image).into(imageView)
        yas.text = pilot.age.toString()
        takim.text = pilot.team
        favControl(pilot)
    }

    private fun favControl(pilot: Pilot?){
        val favList = db!!.dao().getAll()
        var isFav : Boolean = false
        for (fav in favList){
            if (pilot!!.id.toString().equals(fav.id.toString())){
                isFav = true
                break
            }
        }
        if (isFav){
            favori.text = "Remove From Favs"
            favori.setOnClickListener {
                db!!.dao().deleteById(pilot!!.id.toString())
                favControl(pilot)
            }
        }else{
            favori.text = "Add To Favs"
            favori.setOnClickListener {
                db!!.dao().insert(pilot!!)

                favControl(pilot)
            }
        }
    }
}
