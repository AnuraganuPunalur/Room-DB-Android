package com.apps.anurag.myroomdb.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

/**
 *Created by anurag on 19,February,2019
 */
@Entity(tableName = "social_user")
class SocialUser(

    @ColumnInfo(name = "platform_name")
    private var socialPlatForm: String?,
    @ColumnInfo(name = "social_name")
    private var socialName: String?

) {

    @NonNull
    @PrimaryKey (autoGenerate = true)
    private var id : Int = 0

    fun getSocialPlatForm() : String?{

       return socialPlatForm
   }

//    fun setSocialPlatForm(socialPlatform : String){
//
//        this.socialPlatForm = socialPlatform
//    }

     fun getSocialName() : String?{

        return socialName
    }

//    fun setSocialName(socialName : String){
//
//        this.socialName = socialName
//    }

     fun getId() : Int{

        return id
    }

    fun setId(id : Int){

        this.id = id
    }

}