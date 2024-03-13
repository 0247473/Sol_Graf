package com.example.peliculas

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.database.database

class Agregar : AppCompatActivity() {
    val database = Firebase.database
    val myRef = database.getReference("peliculas")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)

        var nombre = findViewById<EditText>(R.id.nombrea)
        var genero = findViewById<EditText>(R.id.generoa)
        var anio = findViewById<EditText>(R.id.anioa)
        var agregar = findViewById<Button>(R.id.agregara)

        agregar.setOnClickListener {
            var pelicula = PeliCampos(nombre.text.toString(),genero.text.toString(),anio.text.toString())
            myRef.push().setValue(pelicula).addOnCompleteListener{
                    task ->

                if (task.isSuccessful)
                {
                    Toast.makeText(this, "Exito pelicula agregada", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }
        }
    }
    }
}