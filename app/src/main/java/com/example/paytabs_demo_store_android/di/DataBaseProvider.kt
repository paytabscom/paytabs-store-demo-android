package com.example.paytabs_demo_store_android.di

import android.content.Context
import androidx.room.Room
import com.example.paytabs_demo_store_android.database.PtDatabase

var db: PtDatabase? = null

class DataBaseProvider {

    companion object {
        fun getInstance(appContext: Context): PtDatabase {
            if (db == null) {
                db = Room.databaseBuilder(
                    appContext,
                    PtDatabase::class.java, "pt_database01",
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
            return db!!
        }

    }
}