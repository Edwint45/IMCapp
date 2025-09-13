package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imc.calculadoraimc.ImcActivity
import com.example.imc.saludarapp.SaludarActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        val btnSaludar = findViewById<Button>(R.id.btnSaludar)

        btnSaludar.setOnClickListener {
            navigateToSaludar()
        }

        val btnImc = findViewById<Button>(R.id.btnImc)

        btnImc.setOnClickListener {
            navigateToImc()
        }


    }

    fun navigateToSaludar() {
        //navegar nueva pantalla
        val intent = Intent(this, SaludarActivity::class.java)
        startActivity(intent)
    }

    fun navigateToImc() {
        val intent = Intent(this, ImcActivity::class.java)
        startActivity(intent)

    }
}