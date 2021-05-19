package com.kuntech.zurirvcontactlist.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="sign_up_parameter_table")
data class SignUpParameter(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "full_name_user")
    var fullNameUser: String,
    @ColumnInfo(name = "email_user")
    var email: String,
    @ColumnInfo(name = "password_user")
    var password: String
)
