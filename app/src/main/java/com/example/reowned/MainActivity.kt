package com.example.reowned

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reowned.activity.Profile
import com.example.reowned.adapter.AdapterProduts
import com.example.reowned.api.RetrofitHelper
import com.example.reowned.model.LoginRequest
import com.example.reowned.model.LoginSuccess
import com.example.reowned.model.Product
import com.example.reowned.repository.Repository
import com.example.reowned.sealed.Results
import com.example.reowned.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var textHello : TextView
    lateinit var textProfil : TextView

    val mainViewModel : MainViewModel by lazy { MainViewModel(Repository(RetrofitHelper.apiCall())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //val loginUser = intent.getParcelableExtra<LoginSuccess>("login")

        recyclerView = findViewById(R.id.recyclerView)

        textHello = findViewById(R.id.text_hello)
        textProfil = findViewById(R.id.bt_get_profile)


        textProfil.setOnClickListener {

            startActivity(Intent(applicationContext,Profile::class.java))

        }

        val list = ArrayList<Product>()

        val adapterProduts = AdapterProduts(list)

        recyclerView.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)

        recyclerView.adapter = adapterProduts

        mainViewModel._product.observe(this, Observer {

            when(it){

                is Results.Loding -> {
                    list.clear()
                    adapterProduts.notifyDataSetChanged()
                    textHello.setText("Loading")
                }

                is Results.Succes -> {
                    textHello.setText("")
                    list.addAll(it.data!!.productList )
                    adapterProduts.notifyDataSetChanged()
                }

                is Results.Error -> {
                    list.clear()
                    adapterProduts.notifyDataSetChanged()
                    textHello.setText("${it.error}")
                }

                else -> {  }
            }

        })

        lifecycleScope.launch(Dispatchers.IO){

            mainViewModel.products()

        }




    }



}