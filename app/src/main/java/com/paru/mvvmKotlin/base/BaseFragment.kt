package com.paru.mvvmKotlin.base

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.indeterminateProgressDialog


open class BaseFragment : Fragment() {

    lateinit var mProgressDialog: ProgressDialog
    lateinit var activity: Activity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity = context as Activity
    }

    fun showDialog(message: String) {
        mProgressDialog = activity.indeterminateProgressDialog(message)
        if (!mProgressDialog.isShowing)
            mProgressDialog.show()
    }

    fun cancelDialog() {
        if (mProgressDialog.isShowing)
            mProgressDialog.dismiss()
    }

}

