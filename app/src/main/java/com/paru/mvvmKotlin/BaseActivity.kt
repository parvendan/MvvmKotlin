package com.paru.mvvmKotlin

import android.app.ProgressDialog
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.indeterminateProgressDialog
import android.content.pm.PackageManager
import android.content.pm.PackageInfo


open class BaseActivity : AppCompatActivity() {
    private val DOT = "."
    lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
       // setSupportActionBar(toolbar)
    }

     fun showDialog(message: String) {
        mProgressDialog = indeterminateProgressDialog(message)
        if (!mProgressDialog.isShowing)
            mProgressDialog.show()
    }

     fun cancelDialog() {
        if (mProgressDialog.isShowing)
            mProgressDialog.dismiss()
    }

    fun getinstalledVersion(): String {
        val packageInfo: PackageInfo
        var version = StringBuilder()

        try {
            packageInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), 0)
            version = StringBuilder(packageInfo.versionName)

            while (version.indexOf(DOT) != -1) {
                version.deleteCharAt(version.indexOf(DOT))
            }
            return version.toString()
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return ""
    }
}