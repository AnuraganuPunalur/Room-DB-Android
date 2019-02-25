package com.apps.anurag.myroomdb

import android.databinding.DataBindingUtil
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.apps.anurag.myroomdb.Adapter.SocialAdapter
import com.apps.anurag.myroomdb.DatabaseClient.DatabaseClient
import com.apps.anurag.myroomdb.databinding.ActivitySocialDetailsBinding
import com.apps.anurag.myroomdb.model.SocialUser

class SocialDetailsActivity : BaseActivity() {

    private lateinit var socialDetailsBinding: ActivitySocialDetailsBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var socialAdapter: SocialAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        socialDetailsBinding = DataBindingUtil.setContentView(this,R.layout.activity_social_details)
        linearLayoutManager = LinearLayoutManager(this)
        socialDetailsBinding.recyclerSocial.layoutManager = linearLayoutManager

        viewDetails()

    }


    private fun viewDetails(){

        class GetDetails : AsyncTask<Void, Void, List<SocialUser>>() {

            override fun doInBackground(vararg p0: Void?): List<SocialUser>? {

                val databaseClient = DatabaseClient(this@SocialDetailsActivity)

                val socialList = databaseClient.getInstance(this@SocialDetailsActivity)?.getSocialDatabase()?.
                    getSocialDao()?.getSocialDetails()
                return socialList
            }

            override fun onPostExecute(result: List<SocialUser>?) {
                super.onPostExecute(result)

                if (result != null) {

                    socialAdapter = SocialAdapter(this@SocialDetailsActivity, result)
                    socialDetailsBinding.recyclerSocial.adapter = socialAdapter
                    socialAdapter.notifyDataSetChanged()

                }else{

                    toast("list is null")
                }
            }

        }

        val getDetails = GetDetails()
        getDetails.execute()

    }

}
