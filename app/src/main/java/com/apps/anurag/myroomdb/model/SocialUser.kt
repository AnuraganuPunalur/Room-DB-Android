package com.apps.anurag.myroomdb.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 *Created by anurag on 19,February,2019
 */
@Entity(tableName = "social_user")
class SocialUser {

    @PrimaryKey (autoGenerate = true)
    private var id : Int = 0

    @ColumnInfo (name = "platform_name")
    private var socialPlatForm : String? = null

    @ColumnInfo (name = "social_name")
    private var socialName : String? = null

    constructor(socialPlatForm: String?, socialName: String?) {

        this.socialPlatForm = socialPlatForm
        this.socialName = socialName
    }


    private fun getSocialPlatform() : String?{

        return socialPlatForm
    }

    private fun getSocialName() : String?{

        return socialName
    }

    private fun getId() : Int{

        return id
    }

}