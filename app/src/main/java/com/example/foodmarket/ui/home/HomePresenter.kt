package com.example.foodmarket.ui.home

import com.example.foodmarket.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter (private val view:HomeContract.View) :HomeContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getHome() {

        val disposable = HttpClient.getInstance().getApi()!!.home()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {

                            if (it.meta?.status.equals("success",true)){
                                it.data?.let { it1 -> view.onHomeSuccess(it1) }
                            } else {
                                it.meta?.message?.let { it1 -> view.onHomeFailed(it1) }
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