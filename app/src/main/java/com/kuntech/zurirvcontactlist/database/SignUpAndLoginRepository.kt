package com.kuntech.zurirvcontactlist.database

import android.content.Context

class SignUpAndLoginRepository (context: Context){
    private val db = SignUpDatabase.getInstance(context)
    suspend fun insert(details:SignUpParameter)= db.signUpDatabaseDao.insert(details)
    fun get(email:String,password:String):List<SignUpParameter> = db.signUpDatabaseDao.get(email,password)
}