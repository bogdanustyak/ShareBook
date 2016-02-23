package com.leoart.sharebook.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo

import com.leoart.sharebook.Constants
import com.leoart.sharebook.R
import com.leoart.sharebook.models.BookModel
import com.leoart.sharebook.network.BooksService
import com.leoart.sharebook.network.ServiceFactory

import butterknife.Bind
import butterknife.ButterKnife
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SearchActivity : AppCompatActivity() {

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        ButterKnife.bind(this)
        searchView = findViewById(R.id.search_view) as SearchView
        handleIntent(intent)
        setupSearchView()

        onNewIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        // Retrieve the SearchView and plug it into SearchManager
        val searchView = MenuItemCompat.getActionView(menu.findItem(R.id.action_search)) as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        return true
    }

    public override fun onNewIntent(intent: Intent) {
        // setIntent(intent);
        handleIntent(intent)
    }


    private fun handleIntent(intent: Intent) {
        if (intent.hasExtra(SearchManager.QUERY)) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            if (!TextUtils.isEmpty(query)) {
                searchView?.setQuery(query, false)
                doSearch(query)
            }
        }
    }

    private fun doSearch(queryStr: String) {
        // get a Cursor, prepare the ListAdapter
        // and set it
        Log.d(TAG, queryStr)
        val serviceFactory = ServiceFactory.createRetrofitService(BooksService::class.java, Constants.BASE_URL)
        //        serviceFactory.searchBooks(queryStr)
        //                .subscribeOn(Schedulers.newThread())
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .subscribe(new Observer<String>() {
        //                    @Override
        //                    public void onCompleted() {
        //
        //                    }
        //
        //                    @Override
        //                    public void onError(Throwable e) {
        //
        //                    }
        //
        //                    @Override
        //                    public void onNext(String s) {
        //                        System.out.println("RESP = " + s);
        //                    }
        //                });
        serviceFactory.searchBooks(queryStr).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<List<BookModel>> {
            override fun onCompleted() {
                Log.d(TAG, "onCompleted")
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, e.message)
            }

            override fun onNext(bookModels: List<BookModel>) {
                Log.d(TAG, "onNext")
                println("Books=")
                for (bookModel in bookModels) {
                    println(bookModel.toString())
                }
            }
        })
    }

    private fun setupSearchView() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        // hint, inputType & ime options seem to be ignored from XML! Set in code
        searchView?.queryHint = getString(R.string.search_hint)
        searchView?.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS
        searchView?.imeOptions = searchView?.imeOptions?.or(EditorInfo.IME_ACTION_SEARCH)?.or(EditorInfo.IME_FLAG_NO_EXTRACT_UI)?.or(EditorInfo.IME_FLAG_NO_FULLSCREEN)!!
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                doSearch(query)
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (TextUtils.isEmpty(query)) {
                    // clearResults();
                }
                return true
            }
        })
        searchView?.setOnQueryTextFocusChangeListener { v, hasFocus ->
            //                if (hasFocus && confirmSaveContainer.getVisibility() == View.VISIBLE) {
            //                    hideSaveConfimation();
            //                }
        }
    }

    companion object {

        private val TAG = "SearchActivity"

        val EXTRA_MENU_LEFT = "EXTRA_MENU_LEFT"
        val EXTRA_MENU_CENTER_X = "EXTRA_MENU_CENTER_X"

        fun createStartIntent(context: Context, menuIconLeft: Int, menuIconCenterX: Int): Intent {
            val starter = Intent(context, SearchActivity::class.java)
            starter.putExtra(EXTRA_MENU_LEFT, menuIconLeft)
            starter.putExtra(EXTRA_MENU_CENTER_X, menuIconCenterX)
            return starter
        }
    }
}
