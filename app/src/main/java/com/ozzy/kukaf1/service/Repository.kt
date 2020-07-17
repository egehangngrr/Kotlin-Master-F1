package com.ozzy.kukaf1.service

import com.ozzy.kukaf1.models.Pilot
import com.ozzy.kukaf1.models.responses.DriversResponse
import io.reactivex.Observable

class Repository (private val apiCallInterface: ApiCallInterface){

    fun executeGetDriverList(): Observable<DriversResponse>{
        return apiCallInterface.getDriverList()
    }
    fun executeGetDriverDetail(id:String): Observable<Pilot>{
        return apiCallInterface.getDriverDetail(id)
    }
}