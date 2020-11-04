package com.example.oop

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ClientAPI {
    @GET("company/{cp_name}")
    fun showListModel(
        @Path("cp_name") cp_name: String
    ): Call<List<Model>>

    @GET("brand/{br_name}")
    fun showListLimitedModel(
        @Path("br_name") br_name: String
    ): Call<List<LimitedModel>>

    companion object{
        fun create() : ClientAPI{
            val apiClient : ClientAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ClientAPI ::class.java)
            return apiClient
        }
    }

}