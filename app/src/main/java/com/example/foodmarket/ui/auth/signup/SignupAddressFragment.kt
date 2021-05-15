package com.example.foodmarket.ui.auth.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.foodmarket.FoodMarket
import com.example.foodmarket.R
import com.example.foodmarket.model.request.RegisterRequest
import com.example.foodmarket.model.response.login.LoginResponse
import com.example.foodmarket.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_signup_address.*

class SignupAddressFragment : Fragment(), SignupContract.View {

    lateinit var data:RegisterRequest
    lateinit var presenter: SignupPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignupPresenter(this)

        data = arguments?.getParcelable<RegisterRequest>("data")!!

        initDummy()
        initListener()
    }

    private fun initListener() {

        btnSignUpNow.setOnClickListener { view ->

            var phone = etPhoneNumber.text.toString()
            var address = etAddress.text.toString()
            var houseNumber = etHouseNumber.text.toString()
            var city = etCity.text.toString()
            data.let {
                it.phoneNumber = phone
                it.address = address
                it.houseNumber = houseNumber
                it.city = city
            }

            if (phone.isNullOrEmpty()) {
                etPhoneNumber.error = "Silahkan masukkan nomor handphone anda"
                etPhoneNumber.requestFocus()
            } else if (address.isNullOrEmpty()) {
                etAddress.error = "Silahkan masukkan alamat anda"
                etAddress.requestFocus()
            } else if (houseNumber.isNullOrEmpty()) {
                etHouseNumber.error = "Silahkan masukkan nomor rumah anda"
                etHouseNumber.requestFocus()
            } else if (city.isNullOrEmpty()) {
                etCity.error = "Silahkan masukkan kota anda"
                etCity.requestFocus()
            } else {
                presenter.submitRegister(data, view)
            }
        }
    }

    private fun initDummy(){
        etPhoneNumber.setText("0861882008")
        etAddress.setText("Jl pattimura")
        etHouseNumber.setText("110")
        etCity.setText("Depok")
    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        FoodMarket.getApp().setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().setUser(json)

        if (data.filePath == null) {
            Navigation.findNavController(view)
                    .navigate(R.id.action_signup_success, null)
            (activity as AuthActivity).toolbarSignUpSuccess()
        } else {
            presenter.submitPhotoRegister(data.filePath!!, view)
        }
    }

    override fun onRegisterPhotoSuccess(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_signup_success, null)
        (activity as AuthActivity).toolbarSignUpSuccess()
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }
}