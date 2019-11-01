package com.sample.sampleproject.ui

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.sampleproject.R
import com.sample.sampleproject.models.MyData
import com.sample.sampleproject.ui.adapter.MyAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.view.*

class MainActivity : AppCompatActivity(), MainView {


    override fun showData(body: MyData?) {
        if(body?.title !=null){
            myToolbar.txtTitle.text = body.title
        }
        if (body?.rows != null) {
            recyclerView.adapter = MyAdapter(this,body.rows)
            recyclerView.visibility = View.VISIBLE
        } else {
            showError(getString(R.string.no_data))
        }
    }

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        mainViewModel = MainViewModel(this)
        btnRetry.setOnClickListener {
            groupError.visibility = View.GONE
            callApi()
        }
        myToolbar.imgRight.setOnClickListener {
            groupError.visibility = View.GONE
            callApi()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        callApi()

    }

    private fun callApi() {
        if (isNetworkConnected()) {
            mainViewModel.getData()
        } else {
            showError(getString(R.string.internet_error))
        }
    }

    override fun showError(message: String) {
        txtError.text = message
        groupError.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }
    override fun showLoading() {
        progressbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressbar.visibility = View.GONE
    }
}
