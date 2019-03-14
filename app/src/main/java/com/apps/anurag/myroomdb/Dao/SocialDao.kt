package com.apps.anurag.myroomdb.Dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.apps.anurag.myroomdb.model.SocialUser

/**
 *Created by anurag on 19,February,2019
 */
@Dao
interface SocialDao  {

    @Query("SELECT * FROM social_user")
    fun getSocialDetails() : List<SocialUser>?

    @Insert
    fun insertSocial(socialUser: SocialUser)

    @Update
    fun updateSocial(socialUser: SocialUser)

    @Query("UPDATE social_user SET platform_name = :platForm, social_name = :userName WHERE id = :entryID")
    fun updateEntry(entryID : Int,platForm : String,userName : String)

    @Query("DELETE FROM social_user WHERE id = :entryID")
    fun deleteSocial(entryID: Int)


}