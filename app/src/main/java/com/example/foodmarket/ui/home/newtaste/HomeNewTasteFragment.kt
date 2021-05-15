package com.example.foodmarket.ui.home.newtaste

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarket.R
import com.example.foodmarket.model.dummy.HomeVerticalModel
import com.example.foodmarket.model.response.home.Data
import com.example.foodmarket.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home_new_teste.*
import java.util.*

class HomeNewTasteFragment : Fragment() , HomeNewtasteAdapter.ItemAdapterCallback {

    private var newTasteList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_new_teste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newTasteList = arguments?.getParcelableArrayList("data")

        var adapter = HomeNewtasteAdapter(newTasteList!!, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcList.layoutManager = layoutManager
        rcList.adapter = adapter
    }

    override fun onClick(v: View?, data: Data) {
        val detail = Intent(activity,DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }
}