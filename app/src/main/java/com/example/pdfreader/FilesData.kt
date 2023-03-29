package com.example.pdfreader

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filesData")
data class FilesData(

    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val name: String,
    val phone: String

)