package com.example.oop

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Model(
    @Expose
    @SerializedName("md_id") val md_id: Int,
    @Expose
    @SerializedName("md_name") val md_name: String
){

}