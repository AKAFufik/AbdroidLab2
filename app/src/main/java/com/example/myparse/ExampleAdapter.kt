package com.example.myparse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.squareup.picasso.Picasso

class ExampleAdapter(val context: Context, val list: ArrayList<Technology>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false)

        val technologyImage = view.findViewById(R.id.technology_image) as DynamicImageView
        val technologyName = view.findViewById(R.id.technology_name) as TextView

        Picasso.get().load("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/" + list[position].graphic).into(technologyImage)
        technologyName.text = list[position].name

        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}