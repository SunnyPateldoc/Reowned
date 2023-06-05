package com.example.reowned.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.reowned.MainActivity
import com.example.reowned.R
import com.example.reowned.api.RetrofitHelper
import com.example.reowned.model.User
import com.example.reowned.repository.Repository
import com.example.reowned.sealed.Results
import com.example.reowned.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Registrastion : AppCompatActivity() {

    lateinit var firstName : EditText
    lateinit var lastName : EditText
    lateinit var age : EditText
    lateinit var error : TextView
    lateinit var register : TextView

    val mainViewModel : MainViewModel by lazy { MainViewModel(Repository(RetrofitHelper.apiCall())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrastion)

        firstName = findViewById(R.id.edt_first_name)
        lastName = findViewById(R.id.edt_last_name)
        age = findViewById(R.id.edt_age)

        error = findViewById(R.id.text_hello)

        register = findViewById(R.id.bt_register)

        register.setOnClickListener {

            val fistName = firstName.text.toString()
            val lastName = lastName.text.toString()
            val age = age.text.toString()

            if (fistName.isEmpty()){

                error.setText("First Name is Empty!")

            }else if (lastName.isEmpty()){

                error.setText("Last Name is Empty!")

            }else if (age.isEmpty()){

                error.setText("Age is Empty!")

            }else{

                lifecycleScope.launch(Dispatchers.IO){

                    mainViewModel.addUser(User(null,fistName,lastName,age.toInt(),null,null,null,null))

                }

            }

        }


        mainViewModel._addUser.observe(this, Observer {

            when(it){

                is Results.Loding -> { error.setText("Loading") }

                is Results.Succes -> {

                    error.setText("${it.data}")
                    Log.w(javaClass.name,"${it.data}")

                    /*startActivity(Intent(applicationContext, Lo::class.java))
                    finish()*/
                }

                is Results.Error -> { error.setText("${it.error}") }

                else -> {  }
            }


        })


    }


}