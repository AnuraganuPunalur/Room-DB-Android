package com.apps.anurag.myroomdb

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 *Created by anurag on 25,February,2019
 */
open class BaseActivity : AppCompatActivity() {


    fun toast(msg : String){

        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }
}