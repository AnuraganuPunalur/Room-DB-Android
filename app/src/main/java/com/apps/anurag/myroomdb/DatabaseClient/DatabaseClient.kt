package com.apps.anurag.myroomdb.DatabaseClient

import android.arch.persistence.room.Room
import android.content.Context
import com.apps.anurag.myroomdb.Database.SocialDatabase



/**
 *Created by anurag on 25,February,2019
 */
class DatabaseClient(context: Context) {


    private var mInstance: DatabaseClient? = null
    private var socialDatabase: SocialDatabase? = null
    //our app database object

    init {

        socialDatabase = Room.databaseBuilder(context, SocialDatabase::class.java, "SocialDB").build()

        //creating the app database with Room database builder
        //MyToDos is the name of the database
    }

    @Synchronized
    fun getInstance(context: Context): DatabaseClient? {
        if (mInstance == null) {

            mInstance = DatabaseClient(context)
        }
        return mInstance
    }

    fun getSocialDatabase(): SocialDatabase? {

        return socialDatabase
    }
}

