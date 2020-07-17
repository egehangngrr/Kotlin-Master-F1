package com.ozzy.kukaf1.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonParser
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.ozzy.kukaf1.models.Pilot
import com.ozzy.kukaf1.models.responses.DriversResponse
import com.ozzy.kukaf1.service.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.lang.Exception

class PilotsViewModel(private val repository: Repository) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val driverData = MutableLiveData<DriversResponse>()
    private val driverDetailData = MutableLiveData<Pilot>()

    fun driverListResponse(): MutableLiveData<DriversResponse> {
        return driverData
    }

    fun driverDetailResponse(): MutableLiveData<Pilot>{
        return driverDetailData
    }

    fun getDriverList(){
        disposables.add(repository.executeGetDriverList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { d ->
            }
            .subscribe(
                { result ->
                    driverData.setValue(result)
                },
                {
                    Log.d("AAAAAAAAa", it.localizedMessage)
                }
            ))
    }

    fun getDriverDetail(id : String){
        disposables.add(repository.executeGetDriverDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { d ->
            }
            .subscribe(
                { result ->
                    driverDetailData.setValue(result)
                },
                {
                    Log.d("AAAAAAAAa", it.localizedMessage)
                }
            ))
    }
}
