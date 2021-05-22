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
import com.kuntech.zurirvcontactlist.databinding.ActivityMainBinding
import com.kuntech.zurirvcontactlist.databinding.ActivitySignUpBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var repository: SignUpAndLoginRepository
   // private lateinit var dao: SignUpDatabaseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)



         val dao= SignUpDatabase.getInstance(this).signUpDatabaseDao
        repository = SignUpAndLoginRepository(this)

        binding.btnSignup.setOnClickListener {

            val fullName = binding.etFullnameText.text.toString()
            val email = binding.etEmailText.text.toString()
            val password = binding.etPasswordText.text.toString()

            if(fullName.isNotEmpty() &&email.isNotEmpty() && password.isNotEmpty()){
                lifecycleScope.launch {
            //dao.insert(SignUpParameter(fullName,email,password))
                    repository.insert(SignUpParameter(fullName,email,password))
               }
                Toast.makeText(this, "SignUp successful", Toast.LENGTH_SHORT)
                .show()
                CoroutineScope(Dispatchers.Main).launch {
                    setUpIntent()
                }
            }
            else{
                Toast.makeText(this, "Please fill in all the required information", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
        }

        binding.tvSignin.setOnClickListener {
            Intent(this,LoginActivity::class.java).also {
                startActivity(it)
            }
        }

    }

    suspend  fun setUpIntent(){
        delay(2000L)
        Intent(this,LoginActivity::class.java).also {
            startActivity(it)
        }

    }
}