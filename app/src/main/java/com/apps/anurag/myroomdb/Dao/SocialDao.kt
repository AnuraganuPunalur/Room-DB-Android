package com.apps.anurag.myroomdb.Dao

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.apps.anurag.myroomdb.model.SocialUser

/**
 *Created by anurag on 19,February,2019
 */
interface SocialDao  {

    @Query("SELECT * FROM social_user")
    fun getSocialDetails() : List<SocialUser>

    @Insert
    fun insertSocial(socialUser: SocialUser)

    @Update
    fun updateSocial(socialUser: SocialUser)

    @Delete
    fun deleteSocial(socialUser: SocialUser)


}