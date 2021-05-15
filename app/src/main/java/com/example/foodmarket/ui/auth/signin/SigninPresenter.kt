package com.example.foodmarket.ui.auth.signin

import com.example.foodmarket.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SigninPresenter (private val view:SigninContract.View) :SigninContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitLogin(email: String, password: String) {

        val disposable = HttpClient.getInstance().getApi()!!.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {

                            if (it.meta?.status.equals("success",true)){
                                it.data?.let { it1 -> view.onLoginSuccess(it1) }
                            } else {
                                it.meta?.message?.let { it1 -> view.onLoginFailed(it1) }
                            }
                        }
                )
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }
}