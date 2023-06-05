package com.example.reowned.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
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
class Profile : AppCompatActivity() {

    lateinit var textEmail: TextView
    lateinit var textId: TextView
    lateinit var textName: TextView
    lateinit var textUserName: TextView
    lateinit var textGender: TextView
    lateinit var textError: TextView

    lateinit var imgProfile: ImageView

    val mainViewModel : MainViewModel by lazy { MainViewModel(Repository(RetrofitHelper.apiCall())) }

    private val keyLogin = "isLogin"
    private val keyId = "id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        textId = findViewById(R.id.text_id)
        textName = findViewById(R.id.text_name)
        textUserName = findViewById(R.id.text_username)
        textEmail = findViewById(R.id.text_email)
        textGender = findViewById(R.id.text_gender)
        textError = findViewById(R.id.text_error)

        imgProfile = findViewById(R.id.img_user_profile)

        val loginUser = getSharedPreferences("login",Context.MODE_PRIVATE)

        val getLogin = loginUser.getString(keyId,"")

        if (!getLogin!!.isEmpty()){

            lifecycleScope.launch(Dispatchers.IO){

                mainViewModel.getUser(getLogin)


            }

        }

        mainViewModel._user.observe(this , Observer {

            when(it){

                is Results.Loding -> { clearUserData() }

                is Results.Succes -> { setUserData(it.data) }

                is Results.Error -> { clearUserData()
                    textError.setText("${it.error}")
                }

                else -> { clearUserData() }
            }

        })


        /*val id = loginUser?.id
        val firstName = loginUser?.firstName
        val lastName = loginUser?.lastName
        val email = loginUser?.email
        val username = loginUser?.username
        val image = loginUser?.image
        val gender = loginUser?.gender
        val token = loginUser?.token


        textId.setText("$id")
        textName.setText("$firstName $lastName")
        textUserName.setText("$username")
        textEmail.setText("$email")
        textGender.setText("$gender")


        Glide.with(this).load(image).circleCrop().into(imgProfile)
*/

    }

    private fun clearUserData(){

        textId.setText("User Id : ")
        textName.setText("Name : ")
        textUserName.setText("Email : ")
        textEmail.setText("Phone : ")
        textGender.setText("Gender : ")

        Glide.with(this).load("").circleCrop().into(imgProfile)


    }

    private fun setUserData(user: User?){

        textId.setText("User Id : ${user?.id}")
        textName.setText("Name : ${user?.firstName} ${user?.lastName}")
        textUserName.setText("Email : ${user?.email}")
        textEmail.setText("Phone : ${user?.phone}")
        textGender.setText("Gender : ${user?.gender}")

        Glide.with(this).load(user?.image).circleCrop().into(imgProfile)


    }


}