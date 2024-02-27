package com.example.peliculas

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class PeliAdapter (private val contex: Activity, private val arrayList: ArrayList<Pelicula>)
    : ArrayAdapter<Pelicula>(contex, R.layout.item, arrayList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // return super.getView(position, convertView, parent)

        var inflater: LayoutInflater = LayoutInflater.from(contex)
        val view: View = inflater.inflate(R.layout.item, null)

        view.findViewById<TextView>(R.id.nombre).text = arrayList[position].nombre
        view.findViewById<TextView>(R.id.genero).text = arrayList[position].genero
        view.findViewById<TextView>(R.id.anio).text = arrayList[position].anio

        if(arrayList[position].genero == "terror")
        {
            view.findViewById<ImageView>(R.id.imagen).setImageDrawable(ContextCompat.getDrawable(contex, R.drawable.ter))
        }
        else if(arrayList[position].genero == "comedia")
        {
            view.findViewById<ImageView>(R.id.imagen).setImageDrawable(ContextCompat.getDrawable(contex, R.drawable.dra))
        }
        else {
            view.findViewById<ImageView>(R.id.imagen).setImageDrawable(ContextCompat.getDrawable(contex, R.drawable.pre))
        }
        return view
    }
}