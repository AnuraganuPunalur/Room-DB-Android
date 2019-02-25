package com.apps.anurag.myroomdb.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apps.anurag.myroomdb.BR
import com.apps.anurag.myroomdb.databinding.RecyclerSocialDataBinding
import com.apps.anurag.myroomdb.model.SocialUser

/**
 *Created by anurag on 25,February,2019
 */
class SocialAdapter(var context : Context,var socialList : List<SocialUser>) : RecyclerView.Adapter<SocialAdapter.SocialHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): SocialHolder {

        val socialDataBinding = RecyclerSocialDataBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return SocialHolder(socialDataBinding)
    }

    override fun getItemCount(): Int {

        return socialList.size
    }

    override fun onBindViewHolder(holder: SocialHolder, pos: Int) {

        holder.setBinding(socialList[pos])
    }

    class SocialHolder(var socialDataBinding: RecyclerSocialDataBinding) :
        RecyclerView.ViewHolder(socialDataBinding.root) ,
        View.OnClickListener {

        fun setBinding(socialUser: SocialUser){

            socialDataBinding.setVariable(BR.socialData,socialUser)
            socialDataBinding.executePendingBindings()
        }


        override fun onClick(p0: View?) {


        }

    }
}