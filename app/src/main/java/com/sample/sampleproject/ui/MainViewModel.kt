package com.sample.sampleproject.ui

import com.sample.sampleproject.data.BaseRetrofit
import com.sample.sampleproject.models.MyData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(val mainView: MainView) {

    fun getData() {
        mainView.showLoading()
        BaseRetrofit.apis?.getAllHolidays()?.enqueue(object :Callback<MyData>{
            override fun onFailure(call: Call<MyData>, t: Throwable) {
                mainView.hideLoading()
                mainView.showError(t.message?:"Unknown Error")
            }

            override fun onResponse(call: Call<MyData>, response: Response<MyData>) {
                mainView.hideLoading()
                mainView.showData(response.body())
            }

        })
    }
}