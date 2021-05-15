package com.kuntech.zurirvcontactlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kuntech.zurirvcontactclass.ContactCategoryAdapter
import com.kuntech.zurirvcontactlist.ListCategory.categoryList
import com.kuntech.zurirvcontactlist.databinding.ActivityContactCategoryBinding
import com.kuntech.zurirvcontactlist.databinding.ActivityMainBinding

class ContactCategory : AppCompatActivity() {
    private lateinit var binding: ActivityContactCategoryBinding
    private val adapter = ContactCategoryAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.contactCategoryRv.adapter= adapter
        adapter.setUpContacts(categoryList)

        adapter.setOnItemClickListener {
                setUpIntent(it)
        }
    }
    private fun setUpIntent(contact:ContactType){
        Intent(this,MainActivity::class.java).also {
            it.putExtra("CategoryName",contact.ContactCategoryName)
            startActivity(it)
        }

    }
}