package com.example.foodmarket.ui.home

import com.example.foodmarket.base.BasePresenter
import com.example.foodmarket.base.BaseView
import com.example.foodmarket.model.response.home.HomeResponse

interface HomeContract {

    interface View : BaseView {
        fun onHomeSuccess(homeContract: HomeResponse)
        fun onHomeFailed(message: String)
    }

    interface Presenter : HomeContract, BasePresenter {
        fun getHome()
    }
}