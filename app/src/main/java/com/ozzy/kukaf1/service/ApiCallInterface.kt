package com.ozzy.kukaf1.service

import com.ozzy.kukaf1.models.Pilot
import com.ozzy.kukaf1.models.responses.DriversResponse
import io.reactivex.Observable
import io.reactivex.internal.observers.LambdaObserver
import retrofit2.http.*

interface ApiCallInterface {

    @GET(Urls.DRIVER_LIST)
    fun getDriverList(): Observable<DriversResponse>

    @GET(Urls.DRIVER_DETAIL)
    fun getDriverDetail(@Path("id") id: String ) : Observable<Pilot>
}