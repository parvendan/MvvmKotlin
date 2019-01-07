package com.paru.mvvmKotlin.ui.activity.homeActivity

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.paru.mvvmKotlin.base.BaseActivity
import com.paru.mvvmKotlin.ui.Fragment.FragmentCreateTrip.FragmentCreateTrip
import com.paru.mvvmKotlin.util.Constants
import com.paru.mvvmKotlin.util.PreferenceHelper
import com.paru.mvvmKotlin.util.replaceFragmenty
import com.paru.mvvmKotlin.util.PreferenceHelper.get
import com.paru.mvvmkotlin.R


class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        menuItems()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun menuItems() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        onNavigationItemSelected(navView.getMenu().findItem(R.id.create_bag));

        val headView: View = navView.getHeaderView(0)
        val email: TextView = headView.findViewById(R.id.email_id)
        email.setText(getLoggedInEmail())
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.create_bag -> {
                replaceFragmenty(
                    fragment = FragmentCreateTrip(),
                    allowStateLoss = true,
                    containerViewId = R.id.mainContent
                )
                setTitle("Initial Page")
            }
            R.id.out_scan -> {

            }
            R.id.in_scan -> {

            }
            R.id.change_password -> {

            }
            R.id.logout -> {

            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun getLoggedInEmail():String {
        val prefs = PreferenceHelper.defaultPrefs(this)
        val email_id :String ?= prefs[Constants.LOGGED_IN_EMAIL]
        return email_id.toString()
    }
}
