package com.devspace.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ContactsAdapter : ListAdapter<Contact, ContactsAdapter.ContactViewHolder>(ContactDiffUtil()) {

    private lateinit var onItemClicked: (Contact) -> Unit

    fun setOnClickListener(onClick: (Contact) -> Unit){
        this.onItemClicked = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
    }


    class ContactViewHolder(view: View) : ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.image)
        private val tvName = view.findViewById<TextView>(R.id.tv_name)
        private val tvPhone = view.findViewById<TextView>(R.id.tv_phone)
        private val root = view.rootView

        fun bind(contact: Contact, onClick: (Contact) -> Unit) {
            image.setImageResource(contact.image)
            tvName.text = contact.name
            tvPhone.text = contact.phone

            root.setOnClickListener {
                onClick.invoke(contact)
            }
        }
    }

    private class ContactDiffUtil: DiffUtil.ItemCallback<Contact>() {

        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
           return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
