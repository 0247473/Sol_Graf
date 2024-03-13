package com.example.peliculas

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.oAuthCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MenuPrincipal : AppCompatActivity() {

    lateinit var pelicuas: ArrayList<Pelicula>
    private lateinit var auth: FirebaseAuth
    val database = Firebase.database
    val myRef = database.getReference("peliculas")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        auth = Firebase.auth
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var lista = findViewById<ListView>(R.id.lista)
        lista.setOnItemClickListener{ parent, view, position, id ->

            startActivity(Intent(this, Detalle::class.java)
                .putExtra("id", pelicuas[position].id)
                .putExtra("nombre", pelicuas[position].nombre)
                .putExtra("genero", pelicuas[position].genero)
                .putExtra("anio", pelicuas[position].anio))
        }

        val menuHost: MenuHost = this
        menuHost.addMenuProvider(object:MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
            }

            

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId)
                {
                    R.id.salir -> {
                        auth.signOut()
                        finish()
                        true
                    }
                    R.id.perfil -> {
                        true
                    }
                    else -> false
                }
            }
        })



        myRef.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                pelicuas = ArrayList<Pelicula>()
                val value = snapshot.value
                Log.d(TAG, "Value is: " + value)

                snapshot.children.forEach{
                    hijo ->
                    var pelicula: Pelicula = Pelicula(hijo.child("nombre").value.toString(), hijo.child("genero").value.toString(), hijo.child("anio").value.toString(), hijo.key.toString())
                    pelicuas.add(pelicula)
                }
                llenaLista()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        val btnAgregar = findViewById<FloatingActionButton>(R.id.agregar)

        btnAgregar.setOnClickListener{
            startActivity(Intent(this, Agregar::class.java))
        }

    }

    public fun llenaLista(){
        val adaptador = PeliAdapter(this, pelicuas)
        var lista = findViewById<ListView>(R.id.lista)
        lista.adapter = adaptador
    }


    private fun setSupportActionBar(toolbar: Toolbar?) {

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            auth.signOut()
        }
        return super.onKeyDown(keyCode, event)
    }
}