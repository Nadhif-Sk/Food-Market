package com.example.foodmarket.ui.auth.signin

import com.example.foodmarket.base.BasePresenter
import com.example.foodmarket.base.BaseView
import com.example.foodmarket.model.response.login.LoginResponse

interface SigninContract {

    interface View : BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message: String)
    }

    interface Presenter : SigninContract, BasePresenter {
        fun submitLogin(email:String, password:String)
    }
}