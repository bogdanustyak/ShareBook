package com.leoart.sharebook.discover

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leoart.sharebook.R

class DiscoverFragment : Fragment() {

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_discover, container, false)
        initUI(view)
        return view
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initUI(view: View) {
        viewPager = view.findViewById(R.id.viewpager) as ViewPager
        setupViewPager(viewPager as ViewPager)

        tabLayout = view.findViewById(R.id.tabs) as TabLayout
        tabLayout!!.setupWithViewPager(viewPager as ViewPager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = DiscoverPagerAdapter(activity.supportFragmentManager)
        adapter.addFragment(DiscoverAllFragment.newInstance(), getString(R.string.all))
        adapter.addFragment(DiscoverAllFragment.newInstance(), getString(R.string.friends))
        viewPager.adapter = adapter
    }

    companion object {

        private val TAG = "DiscoverFragment"

        fun newInstance(): DiscoverFragment {
            val fragment = DiscoverFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

}
