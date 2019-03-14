package com.apps.anurag.myroomdb

import android.app.ActionBar
import android.app.Dialog
import android.databinding.DataBindingUtil
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.apps.anurag.myroomdb.Adapter.SocialAdapter
import com.apps.anurag.myroomdb.DatabaseClient.DatabaseClient
import com.apps.anurag.myroomdb.Interfaces.EditEntryListener
import com.apps.anurag.myroomdb.databinding.ActivitySocialDetailsBinding
import com.apps.anurag.myroomdb.model.SocialUser


class SocialDetailsActivity : BaseActivity() , EditEntryListener {

    private lateinit var socialDetailsBinding: ActivitySocialDetailsBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var socialAdapter: SocialAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var socialList : MutableList<SocialUser>
    private lateinit var simpleItemTouchCallback : ItemTouchHelper.Callback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        socialDetailsBinding = DataBindingUtil.setContentView(this,R.layout.activity_social_details)
        linearLayoutManager = LinearLayoutManager(this)
        socialDetailsBinding.recyclerSocial.layoutManager = linearLayoutManager
        socialList = mutableListOf()

        swipingFeature()

        viewDetails()

        toast("Swipe Left/Right to remove items")

        itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(socialDetailsBinding.recyclerSocial)

    }

    //Function for getting details from DB
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

                    socialList = result as MutableList<SocialUser>
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

        showDialog(id)

    }

    //Function for editing particular entry from DB
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
        val window = dialog.window
        val wlp = window?.attributes
        wlp?.gravity = Gravity.CENTER
        wlp?.flags = wlp?.flags?.and(WindowManager.LayoutParams.FLAG_BLUR_BEHIND.inv())
        window?.attributes = wlp
        dialog.window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        dialog.show()


        val platform : EditText = dialog.findViewById(R.id.etDialogSocial)
        val userName : EditText = dialog.findViewById(R.id.etDialogUser)
        val update : Button = dialog.findViewById(R.id.btDialogUpdate)

        update.setOnClickListener {

            editEntry(id,platform.text.toString(),userName.text.toString())
            dialog.dismiss()
        }

    }

    //Function for deleting entry from DB
    private fun deleteEntry(id : Int){

        class DeleteClass : AsyncTask<Void,Void,Void>(){

            override fun doInBackground(vararg p0: Void?): Void? {


              val databaseClient = DatabaseClient(this@SocialDetailsActivity)

                 databaseClient.getInstance(this@SocialDetailsActivity)?.
                  getSocialDatabase()?.getSocialDao()?.deleteSocial(id)

                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)

                toast("removed")
                viewDetails()
            }
        }

        val deleteEntry = DeleteClass()
        deleteEntry.execute()
    }

    //Function for enabling swipe action for recyclerView
    private fun swipingFeature(){

        simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                Toast.makeText(this@SocialDetailsActivity, "on Move", Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                //Remove swiped item from list and notify the RecyclerView

                val position = viewHolder.adapterPosition
                val entryID = socialList[position].getId()

                deleteEntry(entryID)


            }
        }
    }

}
