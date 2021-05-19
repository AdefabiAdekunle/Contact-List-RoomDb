package com.kuntech.zurirvcontactlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.kuntech.zurirvcontactlist.database.SignUpDatabase
import com.kuntech.zurirvcontactlist.database.SignUpDatabaseDao
import com.kuntech.zurirvcontactlist.database.SignUpParameter
import com.kuntech.zurirvcontactlist.databinding.ActivityLoginBinding
import com.kuntech.zurirvcontactlist.databinding.ActivitySignUpBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dao: SignUpDatabaseDao = SignUpDatabase.getInstance(this).signUpDatabaseDao

        binding.btnLogin.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            var resultInfo = ""
             CoroutineScope(Dispatchers.IO).launch{resultInfo = dao.get(email,password).toString()

                 withContext(Dispatchers.Main){
                     if(email.isNotEmpty() && password.isNotEmpty()){
                         intentOperation(resultInfo)
                     }

                     else{
                         makeToast()
                     }
                 }

             }


        }
    }
    private fun intentOperation(resultInfo:String){
        if( resultInfo.length>3){
            Intent(this,ContactCategory::class.java).also {
                startActivity(it)
            }
        }
        else{
            Toast.makeText(this, "Login Parameters not found!! please SignUp to Login", Toast.LENGTH_LONG)
                    .show()
        }
    }

    private fun makeToast(){
        Toast.makeText(this, "Please enter your email and password", Toast.LENGTH_LONG)
                .show()
    }
}