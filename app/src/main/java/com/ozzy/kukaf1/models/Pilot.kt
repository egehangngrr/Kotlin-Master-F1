package com.ozzy.kukaf1.models
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pilotlist")
data class Pilot(
    @PrimaryKey(autoGenerate = true)
    var dbId : Int = 0,

    @SerializedName("age")
    @ColumnInfo(name = "age")
    var age: Int = 0,

    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("image")
    @ColumnInfo(name = "image")
    var image: String = "",

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String = "",

    @SerializedName("point")
    @ColumnInfo(name = "point")
    var point: Int = 0,

    @SerializedName("team")
    @ColumnInfo(name = "team")
    var team: String = ""
)