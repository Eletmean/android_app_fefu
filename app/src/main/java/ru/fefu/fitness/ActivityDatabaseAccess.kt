package ru.fefu.fitness

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDatabaseAccess {

    @Query("SELECT * FROM activities WHERE isFromOtherUser = 0")
    fun getMyActivities(): Flow<List<Activity>>

    @Query("SELECT * FROM activities WHERE isFromOtherUser = 1")
    fun getUsersActivities(): Flow<List<Activity>>

    @Insert
    suspend fun addNewActivity(activity: Activity)

    @Query("DELETE FROM activities")
    suspend fun clearAllActivities()
}