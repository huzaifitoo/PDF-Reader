package com.example.pdfreader

import androidx.room.*

@Dao
interface FilesDao {

    @Insert
    suspend fun insertFile(filesData: FilesData)

    @Update
    suspend fun updateFile(filesData: FilesData)

    @Delete
    suspend fun deleteFile(filesData: FilesData)

    @Query("SELECT * FROM file")
    fun getFilesData() : List<FilesData>
}