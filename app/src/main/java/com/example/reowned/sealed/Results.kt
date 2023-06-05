package com.example.reowned.sealed

sealed class Results<out T>{

    object Loding : Results<Nothing>()

    data class Succes<out T>(val data : T) : Results<T>()

    data class Error(val error : String) : Results<Nothing>()

}
