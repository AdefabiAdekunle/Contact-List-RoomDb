package com.kuntech.zurirvcontactlist

object ListCategory {
    val categoryList = mutableListOf<ContactType>(
           ContactType(R.drawable.ic_family,"Family"),
        ContactType(R.drawable.ic_friends,"Friends"),
        ContactType(R.drawable.ic_work,"Colleague"),
        ContactType(R.drawable.ic_schoolmate,"School Mate"),
        ContactType(R.drawable.ic_customers,"Customers"),
        ContactType(R.drawable.ic_lovers,"Lovers"),
        ContactType(R.drawable.ic_image,"Haters")
    )
}