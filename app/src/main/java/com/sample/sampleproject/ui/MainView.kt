package com.sample.sampleproject.ui

import com.sample.sampleproject.models.MyData

interface MainView {
    fun showData(body: MyData?)
    fun showError(message: String)
    fun showLoading()
    fun hideLoading()
}