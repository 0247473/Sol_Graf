package com.example.peliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.Firebase
import com.google.firebase.database.database

class Detalle : AppCompatActivity() {

    val database = Firebase.database
    val myRef = database.getReference("peliculas")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        var nombre = findViewById<EditText>(R.id.nombred)
        var genero = findViewById<EditText>(R.id.generod)
        var anio = findViewById<EditText>(R.id.aniod)
        var imagen = findViewById<ImageView>(R.id.imagenb)
        var editar = findViewById<Button>(R.id.editard)
        var eliminar = findViewById<Button>(R.id.eliminard)
        var parametros = intent.extras

        nombre.setText(parametros?.getCharSequence("nombre".toString()))
        genero.setText(parametros?.getCharSequence("genero".toString()))
        anio.setText(parametros?.getCharSequence("anio".toString()))

        if(parametros?.getCharSequence("genero")== "terror")
        {
            imagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ter))
        }
        else if(parametros?.getCharSequence("genero")== "comedia")
        {
            imagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dra))
        }
        else {
            imagen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pre))
        }

        editar.setOnClickListener{
            var pelicula = PeliCampos(nombre.text.toString(),genero.text.toString(),anio.text.toString())
            myRef.child(parametros?.getCharSequence("id").toString()).setValue(pelicula).addOnCompleteListener {
                task ->

                if (task.isSuccessful)
                {
                    Toast.makeText(this, "Exito pelicula editada", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}