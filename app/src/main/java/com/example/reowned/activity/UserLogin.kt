package com.example.reowned.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.reowned.MainActivity
import com.example.reowned.R
import com.example.reowned.api.RetrofitHelper
import com.example.reowned.model.LoginRequest
import com.example.reowned.repository.Repository
import com.example.reowned.sealed.Results
import com.example.reowned.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserLogin : AppCompatActivity() {



    lateinit var btLogin : TextView

    lateinit var textHello : TextView

    lateinit var edtUserName : EditText
    lateinit var edtPassword : EditText

    lateinit var textCreatUser : TextView

    private val keyLogin = "isLogin"
    private val keyId = "id"

   val mainViewModel : MainViewModel by lazy { MainViewModel(Repository(RetrofitHelper.apiCall())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreferences = getSharedPreferences("login",Context.MODE_PRIVATE)

        btLogin = findViewById(R.id.bt_get_start)

        textHello = findViewById(R.id.text_hello)

        textCreatUser = findViewById(R.id.text_register)

        edtUserName = findViewById(R.id.edt_email)

        edtPassword = findViewById(R.id.edt_password)

        textCreatUser.setOnClickListener {

            startActivity(Intent(applicationContext,Registrastion::class.java))

        }

        btLogin.setOnClickListener {

            val userName = edtUserName.text.toString()
            val password = edtPassword.text.toString()

            if (userName.isEmpty()){

                textHello.setText("Username is empty!")

            }else if (password.isEmpty()){

                textHello.setText("Password is empty")

            }else {

                lifecycleScope.launch(Dispatchers.IO){

                    mainViewModel.loginUser(LoginRequest(userName,password))

                }

            }

        }

        mainViewModel._login.observe(this, Observer {

            when(it){

                is Results.Loding -> { textHello.setText("Loading") }

                is Results.Succes -> {

                    val edit = sharedPreferences.edit()
                    edit.putString(keyId,"${it.data?.id}")
                    edit.apply()

                    Log.w(javaClass.name,"${it.data}")

                    startActivity(Intent(applicationContext,MainActivity::class.java))
                    finish()
                }

                is Results.Error -> { textHello.setText("${it.error}") }

                else -> {  }
            }


        })


    }


}