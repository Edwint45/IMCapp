package com.example.imc.calculadoraimc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imc.R
import com.example.imc.calculadoraimc.ImcActivity.Companion.IMC_KEY

/**
 * Actividad que muestra el resultado del cálculo del Índice de Masa Corporal (IMC).
 * Recibe el valor del IMC calculado desde la actividad anterior (`ImcActivity`),
 * lo muestra en pantalla y proporciona una descripción y un estado (bajo peso, normal, etc.)
 * basados en ese valor.
 */
class ResultIMCActivity : AppCompatActivity() {

    // Variables para referenciar los componentes de la interfaz de usuario.
    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button


    /**
     * Se llama cuando la actividad es creada por primera vez.
     * Es el punto de entrada para la configuración de la actividad.
     *
     * @param savedInstanceState Si la actividad se está re-inicializando, este Bundle
     * contiene los datos que más recientemente suministró. De lo contrario, es nulo.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilita el modo de pantalla completa (edge-to-edge).
        enableEdgeToEdge()
        // Establece el archivo de diseño XML para esta actividad.
        setContentView(R.layout.activity_result_imcactivity)

        // Recupera el valor del IMC enviado desde la actividad anterior.
        // `intent` contiene los datos pasados entre actividades.
        // Se usa `getFloatExtra` para obtener un valor flotante, con -1.0f como valor por defecto si no se encuentra.
        val result  = intent.extras?.getDouble(IMC_KEY) ?: -1.0

        // Inicializa los componentes de la UI.
        initComponents()

        // Configura los listeners para los eventos de clic.
        initUI(result)

        // Inicializa y muestra la información en la UI basada en el resultado del IMC.
        initListeners()

    }

    /**
     * Configura los listeners para los eventos de clic.
     * En este caso, configura el listener para el botón "Recalcular".
     */
    private fun initListeners() {
        // Asigna un listener de clic al botón de recalcular.
        btnRecalculate.setOnClickListener{
            // Cierra la actividad actual y regresa a la anterior (ImcActivity).
            onBackPressed()
        }
    }

    /**
     * Configura la interfaz de usuario con los resultados del cálculo del IMC.
     * Este método determina el estado del IMC (bajo peso, normal, etc.) y actualiza
     * los TextViews con el valor, el estado y la descripción correspondiente.
     *
     * @param result El valor del IMC (float) calculado.
     */
    private fun initUI(result: Double) {
        // Muestra el valor numérico del IMC, formateado a dos decimales.
        tvIMC.text = result.toString()
        // Estructura de control `when` para determinar el estado del IMC según el rango del resultado.
        when(result){
            in 0.00..18.50 -> {
                // Si el IMC está entre 0 y 18.5
                tvResult.text = getString(R.string.title_bajo_peso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                tvDescription.text = getString(R.string.description_bajo_peso)
            }
            in 18.51..24.99 -> {
                // Si el IMC está entre 18.51 y 24.99
                tvResult.text = getString(R.string.title_peso_normal)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                tvDescription.text = getString(R.string.description_peso_normal)
            }
            in 25.00..29.99 -> {
                // Si el IMC está entre 25.00 y 29.99
                tvResult.text = getString(R.string.title_sobrepeso)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.peso_sobrepeso))
                tvDescription.text = getString(R.string.description_sobrepeso)
            }
            in 30.00..99.00 -> {
                // Si el IMC es 30 o más
                tvResult.text = getString(R.string.title_obesidad)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvDescription.text = getString(R.string.description_obesidad)
            }
            else -> {
                // Si el resultado es un valor no válido (por ejemplo, -1.0)
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }


    /**
     * Inicializa las referencias a los componentes de la interfaz de usuario (vistas).
     * Este método busca las vistas por su ID definido en el archivo XML de diseño
     * y las asigna a las variables de la clase correspondientes.
     */
    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }
}