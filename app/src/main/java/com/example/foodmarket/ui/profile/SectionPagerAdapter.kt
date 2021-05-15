package com.example.foodmarket.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.foodmarket.ui.home.newtaste.HomeNewTasteFragment
import com.example.foodmarket.ui.home.popular.HomePopularFragment
import com.example.foodmarket.ui.home.recommended.HomeRecommendedFragment
import com.example.foodmarket.ui.profile.account.ProfileAccountFragment
import com.example.foodmarket.ui.profile.foodmarket.ProfileFoodmarketFragment

class SectionPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Account"
            1 -> "Food Market"
            else -> null
        }
    }

    override fun getItem(position: Int): Fragment {

        var fragment : Fragment
        return when (position) {
            0 -> {
                fragment = ProfileAccountFragment()
                return fragment
            }
            1 -> {
                fragment = ProfileFoodmarketFragment()
                return fragment
            }
            else -> {
                fragment = ProfileAccountFragment()
                return fragment
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}