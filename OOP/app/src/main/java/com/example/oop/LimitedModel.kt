package com.example.oop

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LimitedModel(
    @Expose
    @SerializedName("lm_id") val lm_id: Int,
    @Expose
    @SerializedName("lm_name") val lm_name: String,
    @Expose
    @SerializedName("lm_price") val lm_price: Int
) {
}