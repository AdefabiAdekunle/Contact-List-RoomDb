package com.kuntech.zurirvcontactlist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SignUpDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(details: SignUpParameter)

    @Query("SELECT * FROM sign_up_parameter_table WHERE email_user = :email AND password_user =:password")
    fun get(email:String,password:String):List<SignUpParameter>

}