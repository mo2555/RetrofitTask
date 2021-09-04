package com.example.retrfoit

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        LoginActivity().finish()

        var sharedPreferences:SharedPreferences = getSharedPreferences("name",0)
        var edit : SharedPreferences.Editor = sharedPreferences.edit()
        edit.putString("string","done")
        edit.commit()

        val url = "https://android-training.appssquare.com/api/"
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val Api: API = retrofit.create(API::class.java)
        val call = Api.getData()
        var item = call.enqueue(object : Callback<ProductsData>{
            override fun onResponse(call: Call<ProductsData>, response: Response<ProductsData>) {

               var data : ArrayList<Data> = response.body()?.data!!
                data= (data+data+data) as ArrayList<Data>
                val adapter = CustomAdapter(data,this@ProductsActivity)
                var recycler : RecyclerView= findViewById(R.id.recyclerView)
        recycler.layoutManager = GridLayoutManager(this@ProductsActivity,2)
        recycler.adapter = adapter
            }

            override fun onFailure(call: Call<ProductsData>, t: Throwable) {
                Toast.makeText(
                    this@ProductsActivity,
                    "Check your network connection",
                    Toast.LENGTH_LONG
                ).show()}
        } )
    }
    override fun onBackPressed() {
        super.onBackPressed()
        LoginActivity().onBackPressed()
    }
}