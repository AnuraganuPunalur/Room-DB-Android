package com.apps.anurag.myroomdb

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.AsyncTask
import android.os.Bundle
import com.apps.anurag.myroomdb.DatabaseClient.DatabaseClient
import com.apps.anurag.myroomdb.databinding.ActivityMainBinding
import com.apps.anurag.myroomdb.model.SocialUser



class MainActivity : BaseActivity() {

    private lateinit var activityMainBinding : ActivityMainBinding
    private lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        activityMainBinding.btAdd.setOnClickListener {

            addDetails()
        }

        activityMainBinding.btView.setOnClickListener {

            val intent = Intent(this,SocialDetailsActivity::class.java)
            startActivity(intent)
        }

    }

    private fun addDetails(){

        when {

            activityMainBinding.etSocialPlatform.text.isNullOrEmpty() -> {

                activityMainBinding.etSocialPlatform.error = getString(R.string.field_empty_error)
                activityMainBinding.etSocialPlatform.requestFocus()
                return

            }
            activityMainBinding.etUsername.text.isNullOrEmpty() -> {

                activityMainBinding.etUsername.error = getString(R.string.field_empty_error)
                activityMainBinding.etUsername.requestFocus()
                return
            }
            else -> {

                 class SaveData : AsyncTask<Void, Void, Void>() {

                    override fun doInBackground(vararg p0: Void?): Void? {


                        val socialUser = SocialUser(activityMainBinding.etSocialPlatform.text.toString(),
                            activityMainBinding.etUsername.text.toString())

//                        socialUser.setSocialPlatForm(activityMainBinding.etSocialPlatform.text.toString())
//                        socialUser.setSocialName(activityMainBinding.etUsername.text.toString())

                        val databaseClient = DatabaseClient(this@MainActivity)

                        databaseClient.getInstance(this@MainActivity)?.getSocialDatabase()?.getSocialDao()?.insertSocial(socialUser)

                        return null
                    }

                    override fun onPostExecute(result: Void?) {
                        super.onPostExecute(result)
                        activityMainBinding.etUsername.text.clear()
                        activityMainBinding.etSocialPlatform.text.clear()
                        activityMainBinding.etSocialPlatform.requestFocus()
                        toast("Details added successfully")

                    }

                }

                val saveData = SaveData()
                saveData.execute()

            }
        }
    }





}
