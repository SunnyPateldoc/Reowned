package com.example.reowned.model

import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("id")
    val id : Int,

    @SerializedName("title")
    val title : String,

    @SerializedName("description")
    val description : String,

    @SerializedName("price")
    val price : String,

    @SerializedName("images")
    val images : ArrayList<String>

)
