package com.example.reowned.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reowned.model.LoginRequest
import com.example.reowned.model.LoginSuccess
import androidx.lifecycle.viewModelScope
import com.example.reowned.model.Product
import com.example.reowned.model.ProductList
import com.example.reowned.model.User
import com.example.reowned.repository.Repository
import com.example.reowned.sealed.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(var repository: Repository) : ViewModel() {

    //user login
    private var login = MutableLiveData<Results<LoginSuccess?>>()
    val _login: LiveData<Results<LoginSuccess?>> get() = login

    // all products
    private var product = MutableLiveData<Results<ProductList?>>()
    val _product: LiveData<Results<ProductList?>> get() = product

    // sigle user get by id
    private var user = MutableLiveData<Results<User?>>()
    val _user: LiveData<Results<User?>> get() = user

    //add User
    private var addUser = MutableLiveData<Results<User?>>()
    val _addUser: LiveData<Results<User?>> get() = addUser

    suspend fun loginUser(loginRequest: LoginRequest) {

        viewModelScope.launch(Dispatchers.IO) {

            login.postValue(Results.Loding)
            login.postValue(repository.getLogin(loginRequest))


        }

    }

    suspend fun products() {

        viewModelScope.launch(Dispatchers.IO) {

            product.postValue(Results.Loding)
            product.postValue(repository.getProducts())

        }

    }

    suspend fun addUser(userData: User) {

        viewModelScope.launch(Dispatchers.IO) {

            addUser.postValue(Results.Loding)
            addUser.postValue(repository.addUser(userData))

        }

    }


    suspend fun getUser(id: String) {

        viewModelScope.launch(Dispatchers.IO) {

            user.postValue(Results.Loding)
            user.postValue(repository.getUser(id))

        }

    }

}