package com.kuntech.zurirvcontactclass

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kuntech.zurirvcontactlist.ContactType
import com.kuntech.zurirvcontactlist.databinding.ContactCategoryItemBinding
import com.kuntech.zurirvcontactlist.databinding.ContactListItemBinding

class ContactCategoryAdapter : RecyclerView.Adapter<ContactCategoryAdapter.ContactCategoryViewHolder>(){
    private val contactCategories = mutableListOf<ContactType>()

    fun setUpContacts(contacts: List<ContactType>) {
        this.contactCategories.addAll(contacts)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactCategoryViewHolder {
        return ContactCategoryViewHolder(
            ContactCategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactCategoryViewHolder, position: Int) {
        val contact = contactCategories[position]
        holder.bindItem(contact)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(contact) }
        }
    }
    override fun getItemCount(): Int {
        return contactCategories.size
    }

    inner class ContactCategoryViewHolder(private val binding: ContactCategoryItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindItem(contact: ContactType){
            binding.imageCategoryName.setImageResource(contact.image)
            binding.categoryNameTv.text = contact.ContactCategoryName
        }
    }
    private var onItemClickListener: ((ContactType)->Unit)? = null

    fun setOnItemClickListener(listener: (ContactType)->Unit){
        onItemClickListener = listener
    }
}