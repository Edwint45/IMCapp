package com.example.imc.calculadoraimc

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imc.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

/**
 * Actividad principal para la calculadora de Índice de Masa Corporal (IMC).
 * Permite al usuario seleccionar el género (masculino o femenino) e interactuar
 * con otros componentes (no implementados completamente en este fragmento) para
 * calcular el IMC.
 */
class ImcActivity : AppCompatActivity() {

    // Variables para rastrear la selección de género.
    // `isMaleSelected` es true si el género masculino está seleccionado.
    private var isMaleSelected:Boolean = true
    // `isFemaleSelected` es true si el género femenino está seleccionado.
    private var isFemaleSelected:Boolean = false

    private var currentWeight: Int = 60

    // Referencias a las vistas CardView para la selección de género.
    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView

    private lateinit var tvHeight:TextView
    private lateinit var rsHeight: RangeSlider

    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight :FloatingActionButton
    private lateinit var tvWeight: TextView
    /**
     * Se llama cuando la actividad es creada por primera vez.
     * Este es el lugar donde se debe realizar la mayor parte de la inicialización:
     * llamar a `setContentView(int)` para inflar la interfaz de usuario de la actividad,
     * usar `findViewById(int)` para interactuar programáticamente con los widgets en la UI,
     * y configurar los listeners.
     *
     * @param savedInstanceState Si la actividad se está re-inicializando después de
     * haber sido previamente cerrada, este Bundle contiene los datos que más recientemente
     * suministró en `onSaveInstanceState(Bundle)`. De lo contrario, es nulo.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Habilita el modo de pantalla completa (edge-to-edge) para la actividad.
        enableEdgeToEdge()
        // Establece el archivo de diseño XML para esta actividad.
        setContentView(R.layout.activity_imc)
        // Inicializa los componentes de la interfaz de usuario.
        initComponents()
        // Configura los listeners para los eventos de clic en las vistas.
        initListeners()
        // Configura el estado inicial de la interfaz de usuario (colores de género).
        initUI()

    }

    /**
     * Inicializa las referencias a los componentes de la interfaz de usuario (vistas).
     * Este método busca las vistas por su ID definido en el archivo XML de diseño
     * y las asigna a las variables de la clase correspondientes.
     */

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)

        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)

        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)
    }

    /**
     * Configura los listeners para los eventos de clic en las vistas de selección de género.
     * Cuando se hace clic en `viewMale` o `viewFemale`, se llama a `changeGender()`
     * y luego a `setGenderColor()` para actualizar la interfaz de usuario.
     */

    private fun initListeners() {

        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }

        rsHeight.addOnChangeListener { _, value, _ ->
            val df = java.text.DecimalFormat("#.##")
            val result = df.format(value)
            tvHeight.text = "$result cm"
        }

        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }

        btnSubtractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }


    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    /**
     * Cambia el estado de selección de género.
     * Invierte los valores booleanos de `isMaleSelected` y `isFemaleSelected`.
     * Si `isMaleSelected` era true, se vuelve false, y viceversa.
     * Lo mismo ocurre con `isFemaleSelected`.
     * Este método asume que solo un género puede estar seleccionado a la vez,
     * aunque la lógica actual de inversión simple podría llevar a estados inconsistentes
     * si no se maneja correctamente (por ejemplo, ambos seleccionados o ninguno).
     *
     * **Nota sobre la lógica actual:** La inversión simple (`!`) en ambos booleanos
     * significa que si `isMaleSelected` es `true` y `isFemaleSelected` es `false`,
     * al hacer clic, se volverán `false` y `true` respectivamente, lo cual es el
     * comportamiento esperado para un cambio de selección. Sin embargo, si ambos
     * fueran `false` inicialmente (un estado no previsto aquí), ambos se volverían `true`.
     * La lógica está pensada para alternar entre los dos estados.
     */
    private fun changeGender(){
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    /**
     * Actualiza el color de fondo de las vistas de selección de género (`viewMale` y `viewFemale`)
     * basándose en si están seleccionadas o no.
     * Utiliza el método `getBackgroundColor()` para determinar el color apropiado.
     */
    private fun setGenderColor(){

        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))

    }

    /**
     * Devuelve el color de fondo apropiado para un componente de género.
     *
     * @param isSelectComponent Booleano que indica si el componente está seleccionado.
     * @return El ID del recurso de color (Int) que se usará para el fondo.
     *         Retorna `R.color.background_component_selected` si `isSelectComponent` es true,
     *         de lo contrario, retorna `R.color.background_component`.
     */
    private fun getBackgroundColor(isSelectComponent:Boolean):Int{
        val colorReference  = if(isSelectComponent){
             R.color.background_component_selected
        }else{
            R.color.background_component

        }
        // Obtiene el valor de color real (entero) a partir del ID del recurso de color.
        return ContextCompat.getColor(this, colorReference)
    }


    /**
     * Inicializa el estado de la interfaz de usuario al iniciar la actividad.
     * En este caso, establece los colores iniciales de las vistas de selección de género
     * llamando a `setGenderColor()`.
     */
    private fun initUI() {
        setGenderColor()
        setWeight()
    }

}