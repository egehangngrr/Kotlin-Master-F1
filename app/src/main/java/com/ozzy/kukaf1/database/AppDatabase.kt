package com.ozzy.kukaf1.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ozzy.kukaf1.models.Pilot

@Database(entities = arrayOf(Pilot::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): Dao
}