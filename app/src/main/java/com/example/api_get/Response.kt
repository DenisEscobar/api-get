package com.example.api_get

import com.google.gson.annotations.SerializedName

data class Response(@SerializedName("status") var status:String,
                    @SerializedName("message") var images: List<String>)
