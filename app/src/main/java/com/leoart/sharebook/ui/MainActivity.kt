package com.leoart.sharebook.ui

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.leoart.sharebook.Constants
import com.leoart.sharebook.R
import com.leoart.sharebook.about.AboutFragment
import com.leoart.sharebook.balance.BalanceFragment
import com.leoart.sharebook.discover.DiscoverFragment
import com.leoart.sharebook.login.LoginActivity
import com.leoart.sharebook.models.User
import com.leoart.sharebook.my_library.MyLibraryFragment
import com.leoart.sharebook.search.SearchActivity
import com.leoart.sharebook.settings.SettingsFragment
import com.leoart.sharebook.utils.FragmentHelper

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var drawer: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        initUser()
        setupNavDrawer()
        showFragment(DiscoverFragment.newInstance())
    }

    private fun initUser() {

        //        String username = intent.getStringExtra(Constants.FACEBOOK_MODELFIELD_USER_NAME);
        //        String email = intent.getStringExtra(Constants.FACEBOOK_MODELFIELD_USER_EMAIL);
        val prefs = getSharedPreferences(Constants.SHARED_KEY, Context.MODE_PRIVATE)
        val username = prefs.getString(Constants.FACEBOOK_MODELFIELD_USER_NAME, "")
        val email = prefs.getString(Constants.FACEBOOK_MODELFIELD_USER_EMAIL, "")
        user = User(username, email)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        // Retrieve the SearchView and plug it into SearchManager
        //        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        //        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        //        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_settings -> {
                showFragment(SettingsFragment.newInstance())
                return true
            }
            R.id.action_search -> {
                val loc = IntArray(2)
                val searchMenuView = toolbar!!.findViewById(R.id.action_search)
                searchMenuView.getLocationOnScreen(loc)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivityForResult(SearchActivity.createStartIntent(this, loc[0], loc[0] + searchMenuView.width / 2), RC_SEARCH, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                } else {
                    startActivityForResult(Intent(this, SearchActivity::class.java), RC_SEARCH)
                }
                searchMenuView.alpha = 0f
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    @SuppressWarnings("StatementWithEmptyBody")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        when (id) {
            R.id.nav_balance -> showFragment(BalanceFragment.newInstance("", ""))
            R.id.nav_discover -> showFragment(DiscoverFragment.newInstance())
            R.id.nav_my_library -> showFragment(MyLibraryFragment.newInstance("", ""))
            R.id.nav_about -> showFragment(AboutFragment.newInstance("", ""))
            R.id.nav_settings -> showFragment(SettingsFragment.newInstance())
            R.id.nav_logout -> goToLoginScreen()
        }

        drawer?.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showFragment(fragment: Fragment) {
        FragmentHelper.getInstance().showFragment(supportFragmentManager, fragment, R.id.container_main)
    }

    private fun goToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun setupNavDrawer() {
        drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer?.setDrawerListener(toggle)
        toggle.syncState()
        setupNavigationView()
    }

    private fun setupNavigationView() {
        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        if (user != null) {
            setUserData(navigationView.getHeaderView(0))
        }
    }

    fun setUserData(view: View) {
        val tvUserName = view.findViewById(R.id.tvUserName) as TextView
        if (!TextUtils.isEmpty(user!!.name)) {
            tvUserName.text = user!!.name
        } else {
            tvUserName.text = ""
        }
        //info_text.setText(data.getEmail().concat(" ").concat(data.getGender()));
        //  Picasso.with(this).load("https://graph.facebook.com/" + data.getId()+ "/picture?type=large").into(ivPicture);*/
    }

    override fun onBackPressed() {
        if (drawer?.isDrawerOpen(GravityCompat.START) as Boolean) {
            drawer?.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private val RC_SEARCH = 0
    }
}