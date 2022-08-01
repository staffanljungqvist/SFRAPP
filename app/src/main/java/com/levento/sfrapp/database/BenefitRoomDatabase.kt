package com.levento.sfrapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.levento.sfrapp.models.BenefitEntity

@Database(entities = [(BenefitEntity::class)], version = 1)
abstract class BenefitRoomDatabase: RoomDatabase() {

    abstract fun benefitDao(): BenefitDao

    companion object {

        private var INSTANCE: BenefitRoomDatabase? = null

        fun getInstance(context: Context): BenefitRoomDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BenefitRoomDatabase::class.java,
                        "benefit_database"
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }
}