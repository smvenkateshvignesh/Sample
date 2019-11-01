package com.sample.sampleproject.data


import com.sample.sampleproject.models.MyData
import retrofit2.Call
import retrofit2.http.*


interface API {

    @GET("s/2iodh4vg0eortkl/facts.js")
    fun getAllHolidays(): Call<MyData>
}