package com.kuntech.zurirvcontactlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.kuntech.zurirvcontactlist.database.SignUpAndLoginRepository
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
    private lateinit var repository: SignUpAndLoginRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dao: SignUpDatabaseDao = SignUpDatabase.getInstance(this).signUpDatabaseDao

        repository = SignUpAndLoginRepository(this)

        binding.btnLogin.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passwordEt.text.toString()
            var resultInfo: List<SignUpParameter>
             CoroutineScope(Dispatchers.IO).launch{resultInfo = repository.get(email,password)
            //resultInfo = dao.get(email,password)
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
    private fun intentOperation(resultInfo:List<SignUpParameter>){
        if(resultInfo.size==1){
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