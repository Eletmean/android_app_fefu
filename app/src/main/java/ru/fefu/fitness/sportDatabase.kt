package ru.fefu.fitness

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Activity::class], version = 4)
abstract class sportDatabase : RoomDatabase() {
    abstract fun activityDao(): ActivityDatabaseAccess
}
