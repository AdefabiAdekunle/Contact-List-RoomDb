package com.kuntech.zurirvcontactlist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SignUpParameter::class],version = 1,exportSchema = false)
abstract class SignUpDatabase : RoomDatabase(){

    abstract val signUpDatabaseDao: SignUpDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: SignUpDatabase? = null

        fun getInstance(context : Context) : SignUpDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SignUpDatabase::class.java,
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance

                }
                return instance
            }
        }
    }
}