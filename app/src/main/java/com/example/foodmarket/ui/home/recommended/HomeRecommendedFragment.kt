package com.example.foodmarket.ui.home.recommended

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarket.R
import com.example.foodmarket.model.dummy.HomeVerticalModel
import com.example.foodmarket.model.response.home.Data
import com.example.foodmarket.ui.detail.DetailActivity
import com.example.foodmarket.ui.home.newtaste.HomeNewtasteAdapter
import kotlinx.android.synthetic.main.fragment_home_new_teste.*
import java.util.ArrayList

class HomeRecommendedFragment : Fragment(), HomeNewtasteAdapter.ItemAdapterCallback {

    private var recommendedList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_new_teste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recommendedList = arguments?.getParcelableArrayList("data")

        var adapter = HomeNewtasteAdapter(recommendedList!!, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter
    }

    override fun onClick(v: View?, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }
}