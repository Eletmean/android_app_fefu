package ru.fefu.fitness

import android.app.Application
import androidx.room.Room

class App : Application() {
    companion object {
        lateinit var INSTANCE: App
    }

    val db: sportDatabase by lazy {
        Room.databaseBuilder(
            this,
            sportDatabase::class.java,
            "database"
        ).fallbackToDestructiveMigration(true).allowMainThreadQueries().build()
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
    }
}