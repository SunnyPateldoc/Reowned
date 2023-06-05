package com.example.reowned.model

import com.google.gson.annotations.SerializedName

data class ProductList(

    @SerializedName("products")
    val productList : ArrayList<Product>
)
