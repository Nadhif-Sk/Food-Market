package com.example.foodmarket.ui.order

import com.example.foodmarket.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class OrderPresenter (private val view:OrderContract.View) :OrderContract.Presenter {

    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getTransaction() {

        val disposable = HttpClient.getInstance().getApi()!!.transaction()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {

                            if (it.meta?.status.equals("success",true)){
                                it.data?.let { it1 -> view.onTransactionSuccess(it1) }
                            } else {
                                it.meta?.message?.let { it1 -> view.onTransactionFailed(it1) }
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