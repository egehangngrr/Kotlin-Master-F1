package com.ozzy.kukaf1.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PilotEntity(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "content") var content: String
)