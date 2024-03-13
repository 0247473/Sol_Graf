package com.example.fragmentos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView.FindListener
import android.widget.Button
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val irCalculadora = findViewById<Button>(R.id.irCalculadora)
        val irIMC = findViewById<Button>(R.id.irIMC)
        remplazarFragmento(Inicio())

        irCalculadora.setOnClickListener {
            remplazarFragmento(Calculadora())
        }

        irIMC.setOnClickListener {
            remplazarFragmento(IndiceMC())
        }
    }

    fun remplazarFragmento(fragmento: Fragment)
    {
        val manager = supportFragmentManager
        var transaccion = manager.beginTransaction()
        transaccion.replace(R.id.fragmentContainerView, fragmento)
        transaccion.commit()
    }
}