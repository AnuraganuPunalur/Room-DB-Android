package com.apps.anurag.myroomdb.Database

import android.arch.persistence.room.Database
import com.apps.anurag.myroomdb.Dao.SocialDao
import com.apps.anurag.myroomdb.model.SocialUser


/**
 *Created by anurag on 19,February,2019
 */
@Database (entities = [SocialUser::class],version = 1)
abstract class SocialDatabase {

abstract fun getSocialDao() : SocialDao
}

