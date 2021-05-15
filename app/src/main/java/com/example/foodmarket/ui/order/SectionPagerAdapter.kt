package com.example.foodmarket.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.foodmarket.model.response.transaction.Data
import com.example.foodmarket.ui.order.inprogress.InprogressFragment
import com.example.foodmarket.ui.order.pastorders.PastordersFragment

class SectionPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var inprogressList:ArrayList<Data>? = ArrayList()
    var pastordersList:ArrayList<Data>? = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "In Progress"
            1 -> "Past Orders"
            else -> null
        }
    }

    override fun getItem(position: Int): Fragment {

        var fragment : Fragment
        return when (position) {
            0 -> {
                fragment = InprogressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = PastordersFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", pastordersList)
                fragment.arguments = bundle
                return fragment
            }
            else -> {
                fragment = InprogressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inprogressList)
                fragment.arguments = bundle
                return fragment
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    fun setData(inprogressListParms:ArrayList<Data>?, pastordersParms:ArrayList<Data>? ) {
        inprogressList = inprogressListParms
        pastordersList = pastordersParms
    }
}