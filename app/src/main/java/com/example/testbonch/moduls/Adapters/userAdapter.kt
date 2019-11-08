package com.example.testbonch.moduls.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testbonch.R
import com.example.testbonch.moduls.dataClases.Albums
import com.example.testbonch.moduls.dataClases.User

class userAdapter(val list: List<User>, val context: Context):
    RecyclerView.Adapter<userAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_user, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val user = list[position]
        holder.bind(user)
    }

    class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val idTV = itemView.findViewById<TextView>(R.id.tv_id)
        private val nameTV = itemView.findViewById<TextView>(R.id.tv_name)
        private val usernameTV =  itemView.findViewById<TextView>(R.id.tv_username)
        private val emailTV = itemView.findViewById<TextView>(R.id.tv_email)
        private val streetTV = itemView.findViewById<TextView>(R.id.tv_address_street)
        private val suiteTV =  itemView.findViewById<TextView>(R.id.tv_address_suite)
        private val cityTV = itemView.findViewById<TextView>(R.id.tv_address_city)
        private val zipcodeTV = itemView.findViewById<TextView>(R.id.tv_address_zipcode)
        private val latTV =  itemView.findViewById<TextView>(R.id.tv_address_geo_lat)
        private val lngTV = itemView.findViewById<TextView>(R.id.tv_address_geo_lng)
        private val phoneTV = itemView.findViewById<TextView>(R.id.tv_phone)
        private val websiteTV =  itemView.findViewById<TextView>(R.id.tv_website)
        private val compNameTV = itemView.findViewById<TextView>(R.id.tv_company_name)
        private val catchPhraseTV = itemView.findViewById<TextView>(R.id.tv_company_catchPhrase)
        private val bsTV =  itemView.findViewById<TextView>(R.id.tv_company_bs)

        fun bind(user: User){
            idTV.text=user.id.toString()
            nameTV.text=user.name
            usernameTV.text=user.username
            emailTV.text=user.email
            streetTV.text=user.address.street
            suiteTV.text=user.address.suite
            cityTV.text=user.address.city
            zipcodeTV.text=user.address.zipcode
            latTV.text=user.address.geo.lat.toString()
            lngTV.text=user.address.geo.lng.toString()
            phoneTV.text=user.phone
            websiteTV.text=user.website
            compNameTV.text=user.company.name
            catchPhraseTV.text=user.company.catchPhrase
            bsTV.text=user.company.bs
        }
    }
}