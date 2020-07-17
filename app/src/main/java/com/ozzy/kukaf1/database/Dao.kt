package com.ozzy.kukaf1.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ozzy.kukaf1.models.Pilot

@Dao
interface Dao {
    @Query("SELECT * FROM pilotlist")
    fun getAll(): List<Pilot>

    @Query("DELETE FROM pilotlist WHERE id = :id")
    fun deleteById(id : String)

    @Insert
    fun insert(user: Pilot)


    @Delete
    fun delete(user: Pilot)
}