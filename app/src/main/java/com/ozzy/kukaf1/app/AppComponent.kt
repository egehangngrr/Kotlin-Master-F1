package com.ozzy.kukaf1.app

import com.ozzy.kukaf1.fragments.FragmentPilotDetail
import com.ozzy.kukaf1.fragments.FragmentPilots
import com.ozzy.kukaf1.service.UtilsModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, UtilsModule::class))
@Singleton
interface AppComponent {
    fun doInjection(fragmentPilots: FragmentPilots)
    fun doInjection(fragmentPilotDetail: FragmentPilotDetail)
}