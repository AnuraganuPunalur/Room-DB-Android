package com.apps.anurag.myroomdb.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.apps.anurag.myroomdb.Dao.SocialDao
import com.apps.anurag.myroomdb.model.SocialUser


/**
 *Created by anurag on 19,February,2019
 */
@Database (entities = [SocialUser::class],version = 2,exportSchema = false)
abstract class SocialDatabase : RoomDatabase(){

   abstract fun getSocialDao() : SocialDao

}

