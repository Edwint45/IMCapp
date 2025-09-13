package com.example.imc.saludarapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imc.R

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)

        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        val name : String = intent.extras?.getString("EXTRA_NAME").orEmpty()

        tvResultado.text = "Hola $name"

    }
}