package com.example.reowned.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginSuccess(

    @SerializedName("id")
    val id : Int,

    @SerializedName("username")
    val username : String,

    @SerializedName("email")
    val email : String,

    @SerializedName("firstName")
    val firstName : String,

    @SerializedName("lastName")
    val lastName : String,

    @SerializedName("gender")
    val gender : String,

    @SerializedName("image")
    val image : String,

    @SerializedName("token")
    val token : String

    ) : Parcelable
