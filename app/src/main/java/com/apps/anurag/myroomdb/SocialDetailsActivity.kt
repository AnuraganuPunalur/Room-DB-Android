package com.apps.anurag.myroomdb

import android.app.Dialog
import android.databinding.DataBindingUtil
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Button
import android.widget.EditText
import com.apps.anurag.myroomdb.Adapter.SocialAdapter
import com.apps.anurag.myroomdb.DatabaseClient.DatabaseClient
import com.apps.anurag.myroomdb.Interfaces.EditEntryListener
import com.apps.anurag.myroomdb.databinding.ActivitySocialDetailsBinding
import com.apps.anurag.myroomdb.model.SocialUser

class SocialDetailsActivity : BaseActivity() , EditEntryListener {

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

                    socialAdapter = SocialAdapter(this@SocialDetailsActivity, result,this@SocialDetailsActivity)
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

    override fun onEditClicked(id: String) {

        toast(id)

        showDialog(id)

        //editEntry(id)
    }


    private fun editEntry(id : String,platformName : String,userName : String){

        class EditClass : AsyncTask<Void,Void,Void>(){

            override fun doInBackground(vararg void: Void?): Void? {

                val databaseClient = DatabaseClient(this@SocialDetailsActivity)

                databaseClient.getInstance(this@SocialDetailsActivity)?.getSocialDatabase()
                    ?.getSocialDao()?.updateEntry(id.toInt(),platformName,userName)

            return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                toast("Updated successfully")

                viewDetails()
            }
        }

        val editClass = EditClass()
        editClass.execute()

    }

    private fun showDialog(id: String){

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.edit_dialog_layout)
        dialog.show()


        val platform : EditText = dialog.findViewById(R.id.etDialogSocial)
        val userName : EditText = dialog.findViewById(R.id.etDialogUser)
        val update : Button = dialog.findViewById(R.id.btDialogUpdate)

        update.setOnClickListener {

            editEntry(id,platform.text.toString(),userName.text.toString())
        }



    }

}
