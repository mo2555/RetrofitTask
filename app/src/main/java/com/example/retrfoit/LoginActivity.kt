package com.example.retrfoit

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi

class LoginActivity : AppCompatActivity(),TextWatcher {
    lateinit var password:EditText
    lateinit var username:EditText
    lateinit var btn:Button
    lateinit var imageView: ImageView
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var sharedPreferences:SharedPreferences = getSharedPreferences("name",0)
        var string:String = sharedPreferences.getString("string","no").toString()
        if (string=="done"){
            var moveToProducts = Intent(this,ProductsActivity::class.java)
            startActivity(moveToProducts)
        }

        btn = findViewById(R.id.button)
        password = findViewById(R.id.password)
        username = findViewById(R.id.username)
        imageView = findViewById(R.id.imageView)
        imageView.visibility = ImageView.VISIBLE
        imageView.animate().translationY(-850f).scaleX(0.7f).scaleY(0.7f).duration=1000
        username.addTextChangedListener(this)
        password.addTextChangedListener(this)

        btn.setOnClickListener {
            if(username.text.toString()=="login@appssquare.com"&&password.text.toString()=="12345678"){
                var moveToProducts = Intent(this,ProductsActivity::class.java)
                startActivity(moveToProducts)
            }
            else
                Toast.makeText(this,"Username or password is wrong!",Toast.LENGTH_LONG).show()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        btn.isEnabled = username.text.isNotEmpty()&&password.text.isNotEmpty()
    }

    override fun afterTextChanged(s: Editable?) {

    }


}