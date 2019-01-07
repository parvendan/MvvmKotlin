package com.paru.mvvmKotlin.ui.activity.loginActivity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.paru.mvvmKotlin.base.BaseActivity
import com.paru.mvvmKotlin.ui.activity.homeActivity.HomeActivity
import com.paru.mvvmKotlin.util.Fields
import kotlinx.android.synthetic.main.content_login.*
import org.json.JSONObject
import android.arch.lifecycle.Observer
import com.paru.mvvmKotlin.network.response.LoginResponse
import com.paru.mvvmKotlin.util.Constants
import com.paru.mvvmKotlin.util.PreferenceHelper
import com.paru.mvvmKotlin.util.PreferenceHelper.set
import com.paru.mvvmkotlin.R


class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val vm: LoginViewModel = ViewModelProviders.of(this)[LoginViewModel::class.java]
        login.setOnClickListener {
            if (vm.isValidLogin(user_name.text.toString(), password.text.toString())) {
                showDialog("Logging in...")
                val postParam = HashMap<String, String>()
                postParam[Fields.USERNAME] = user_name.text.toString()
                postParam[Fields.PASSWORD] = password.text.toString()
                vm.requestLogin(postParam).observe(this, Observer<LoginResponse> {
                    it?.let {
                        if (it.status == Fields.STATUS_SUCCESS) {
                            cancelDialog()
                            saveLoginDetails(Constants.LOGGED_IN_EMAIL, user_name.text.toString())
                            startActivity(Intent(this, HomeActivity::class.java))
                        }

                    }
                })
            } else {
                Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show()
            }
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    //to store the values in sharedPreference
    fun saveLoginDetails(name: String, value: String) {
        val prefs = PreferenceHelper.defaultPrefs(this)
        prefs[name] = value
    }
}
