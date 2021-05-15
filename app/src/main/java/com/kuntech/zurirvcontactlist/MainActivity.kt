package com.kuntech.zurirvcontactlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import com.kuntech.zurirvcontactlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = ContactAdapter()
    private var nameCategory: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nameCategory = intent.getStringExtra("CategoryName")
        title = nameCategory
        setUpData(binding)
    }

    private fun setUpData(binding : ActivityMainBinding){
        binding.contactrv.adapter = adapter
       binding.contactrv.addItemDecoration(DividerItemDecoration(this,LinearLayout.VERTICAL))

        val builder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val view = layoutInflater.inflate(R.layout.add_contact_dialogue,null)
        builder.setView(view)

        val name = view.findViewById<TextView>(R.id.nameEt)
        val no = view.findViewById<TextView>(R.id.numberEt)
        val saveBtn = view.findViewById<Button>(R.id.btnSave)

       no.addTextChangedListener(object:TextWatcher{
           override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

           }

           override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               saveBtn.isEnabled = s?.length == 11
           }

           override fun afterTextChanged(s: Editable?) {
           }

       })

        val alertDialog = builder.create()
        binding.fab?.setOnClickListener{
            alertDialog.show()
        }

        saveBtn.setOnClickListener {
            val contact = Contact(name.text.toString(),no.text.toString())
            val contacts = mutableListOf(contact)
            adapter.setUpContacts(contacts)
            alertDialog.dismiss()
        }

    }
}