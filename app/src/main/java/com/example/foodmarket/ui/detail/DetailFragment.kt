package com.example.foodmarket.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.foodmarket.R
import com.example.foodmarket.model.response.home.Data
import com.example.foodmarket.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.ivPoster
import kotlinx.android.synthetic.main.fragment_detail.tvTitle

class DetailFragment : Fragment() {

    var data:Data?= null
    var bundle:Bundle?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as DetailActivity?)!!.toolbarDetail()

//        data = requireActivity().intent.getParcelableExtra("data")
//        initView(data)

        arguments?.let {
            DetailFragmentArgs.fromBundle(it).data.let {
                initView(it)
            }
        }

        btnOrderNow.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.action_payment, bundle)
        }
    }

    private fun initView(data: Data?) {

        bundle = bundleOf("data" to data)

        Glide.with(requireContext())
            .load(data?.picturePath)
            .into(ivPoster)

        tvTitle.text = data?.name
        tvDesc.text = data?.description
        tvIngredients.text = data?.ingredients

        tvTotal.formatPrice(data?.price.toString())
    }
}