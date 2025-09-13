package com.example.imc.saludarapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imc.R

class SaludarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_saludar)

        val btnStart = findViewById<Button>(R.id.btnStart)
        val etName = findViewById<EditText>(R.id.etName)

        btnStart.setOnClickListener {

            val name = etName.text.toString()

            if(name.isNotEmpty()){
                Log.i("MainActivity", "boton pulsado ${etName.text.toString()}")

                val intent = Intent(this, ResultadoActivity::class.java)
                intent.putExtra("EXTRA_NAME", name)
                startActivity(intent)

            }

        }

    }
}