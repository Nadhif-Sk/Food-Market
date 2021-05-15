package com.example.foodmarket.ui.auth.signin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.foodmarket.FoodMarket
import com.example.foodmarket.R
import com.example.foodmarket.model.response.login.LoginResponse
import com.example.foodmarket.ui.MainActivity
import com.example.foodmarket.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_signin.*

class SigninFragment : Fragment(), SigninContract.View {

    lateinit var presenter: SigninPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SigninPresenter(this)

        if(!FoodMarket.getApp().getToken().isNullOrEmpty()) {
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finish()
        }

        initDummy()

        btnSignup.setOnClickListener{
            val signup = Intent(activity, AuthActivity::class.java)
            signup.putExtra("page_request",2)
            startActivity(signup)
        }

        btnSignin.setOnClickListener {
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()

            if (email.isNullOrEmpty()) {
                etEmail.error = "Silahkan masukkan email Anda"
                etEmail.requestFocus()
            } else if (password.isNullOrEmpty()) {
                etPassword.error = "Silahkan masukkan email Anda"
                etPassword.requestFocus()
            } else {
                presenter.submitLogin(email,password)
            }
        }
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        FoodMarket.getApp().setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(activity,message, Toast.LENGTH_SHORT).show()
    }

    private fun initDummy() {
        etEmail.setText("dani@mail.com")
        etPassword.setText("12345678")
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }
}