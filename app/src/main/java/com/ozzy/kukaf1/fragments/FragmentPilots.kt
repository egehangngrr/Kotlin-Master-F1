package com.ozzy.kukaf1.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ozzy.kukaf1.R
import com.ozzy.kukaf1.adapters.PilotsAdapter
import com.ozzy.kukaf1.app.MyApplication
import com.ozzy.kukaf1.interfaces.RecyclerViewClickListener
import com.ozzy.kukaf1.models.responses.DriversResponse
import com.ozzy.kukaf1.view_models.PilotsViewModel
import com.ozzy.kukaf1.view_models.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_pilots_fragment.*
import javax.inject.Inject

class FragmentPilots : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object {
        fun newInstance() = FragmentPilots()
    }

    private lateinit var viewModel: PilotsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pilots_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity!!.application as MyApplication).appComponent.doInjection(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PilotsViewModel::class.java)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PilotsViewModel::class.java)
        viewModel.driverListResponse().observe(this, Observer<DriversResponse> { this.consumeResponse(it) })
        viewModel.getDriverList()

    }



    private fun consumeResponse(it: DriversResponse?) {

        val adapter = PilotsAdapter(it!!.items!!, object : RecyclerViewClickListener{
            override fun recyclerViewListClicked(v: View, position: Int) {
                val bundle = Bundle()
                bundle.putInt("pilotId", it.items!!.get(position).id)
                Navigation.findNavController(view!!).navigate(R.id.goToDetail, bundle)            }
        },context!!)
        val llm = LinearLayoutManager(context!!.applicationContext)
        pilotsRecyclerView.layoutManager = llm
        pilotsRecyclerView.adapter = adapter
    }

}
