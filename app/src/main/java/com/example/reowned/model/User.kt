package com.example.reowned.model

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("id")
    val id : Int? = null,

    @SerializedName("firstName")
    val firstName : String? = null,

    @SerializedName("lastName")
    val lastName : String? = null,

    @SerializedName("age")
    val age : Int? = null,

    @SerializedName("gender")
    val gender : String? = null,

    @SerializedName("email")
    val email : String? = null,

    @SerializedName("phone")
    val phone : String? = null,

    @SerializedName("image")
    val image : String? = null

    )
