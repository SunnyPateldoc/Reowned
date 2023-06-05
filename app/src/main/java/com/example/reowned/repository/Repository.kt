package com.example.reowned.repository

import com.example.reowned.api.NetworkService
import com.example.reowned.model.LoginRequest
import com.example.reowned.model.LoginSuccess
import com.example.reowned.model.Product
import com.example.reowned.model.ProductList
import com.example.reowned.model.User
import com.example.reowned.sealed.Results
import javax.inject.Inject

class Repository @Inject constructor (var networkService: NetworkService) {

    suspend fun getProducts(): Results<ProductList?> {

       return try {

            val response = networkService.getAllProduct()

            val data = response.execute()

            if (data.isSuccessful){

                Results.Succes(data.body())

            }else{

                Results.Error(data.message())

            }


        }catch (e : Exception){

            Results.Error(e.localizedMessage)

        }

    }

    suspend fun getLogin(login : LoginRequest): Results<LoginSuccess?> {

       return try {

            val response = networkService.login(login)

            val data = response.execute()

            if (data.isSuccessful){

                Results.Succes(data.body())

            }else if (data.code() == 400){

                Results.Error("Invalide Username and Password")

            }else {
                Results.Error(data.message())
            }

        }catch (e : Exception){

            Results.Error(e.localizedMessage)

        }


    }


    suspend fun addUser(userData : User): Results<User?> {

       return try {

            val response = networkService.addUser(userData)

            val data = response.execute()

            if (data.isSuccessful){
                Results.Succes(data.body())
            }else {
                Results.Error(data.message())
            }

        }catch (e : Exception){
            Results.Error(e.localizedMessage)
        }

    }


    suspend fun getUser(id :String): Results<User?> {

        return try {

            val response = networkService.getUsers(id)

            val data = response.execute()

            if (data.isSuccessful){

                Results.Succes(data.body())

            }else{

                Results.Error(data.message())

            }


        }catch (e : Exception){

            Results.Error(e.localizedMessage)

        }

    }


}