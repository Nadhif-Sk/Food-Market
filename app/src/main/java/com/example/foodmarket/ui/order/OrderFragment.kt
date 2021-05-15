package com.example.foodmarket.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.foodmarket.R
import com.example.foodmarket.model.response.transaction.Data
import com.example.foodmarket.model.response.transaction.TransactionResponse
import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class OrderFragment : Fragment(), OrderContract.View {

    lateinit var presenter: OrderPresenter
    var inprogressList:ArrayList<Data>? = ArrayList()
    var pastordersList:ArrayList<Data>? = ArrayList()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_order, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        presenter = OrderPresenter(this)
        presenter.getTransaction()
    }

    private fun initView() {
        include_toolbar.toolbar.title = "Your Orders"
        include_toolbar.toolbar.title = "Wait for the best meal"
    }

    override fun onTransactionSuccess(transactionResponse: TransactionResponse) {
        if (transactionResponse.data.isNullOrEmpty()) {
            ll_empty.visibility = View.VISIBLE
            ll_tab.visibility = View.GONE
            include_toolbar.visibility = View.GONE
        } else {

            for (a in transactionResponse.data.indices) {
                if (transactionResponse.data[a].status.equals("ON_DELIVERY", true)
                    ||transactionResponse.data[a].status.equals("PENDING", true)){
                    inprogressList?.add(transactionResponse.data[a])
                } else if (transactionResponse.data[a].status.equals("DELIVERY", true)
                    ||transactionResponse.data[a].status.equals("CANCELLED", true)
                    ||transactionResponse.data[a].status.equals("SUCCESS", true)){
                    pastordersList?.add(transactionResponse.data[a])
                }
            }

        }
    }

    override fun onTransactionFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun dismissLoading() {
        TODO("Not yet implemented")
    }
}