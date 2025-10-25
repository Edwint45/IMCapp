package com.example.imc.calculadoraimc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
import java.text.DecimalFormat

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

    private var currentAge: Int = 20

    private var currentHeight:Int = 120

    // Referencias a las vistas CardView para la selección de género.
    /** Referencia al CardView para seleccionar el género masculino. */
    private lateinit var viewMale:CardView
    /** Referencia al CardView para seleccionar el género femenino. */
    private lateinit var viewFemale:CardView

    /** Referencia al TextView que muestra el valor actual de la altura. */
    private lateinit var tvHeight:TextView
    /** Referencia al RangeSlider que permite al usuario ajustar la altura. */
    private lateinit var rsHeight: RangeSlider

    /** Referencia al botón flotante para disminuir el valor del peso. */
    private lateinit var btnSubtractWeight: FloatingActionButton
    /** Referencia al botón flotante para aumentar el valor del peso. */
    private lateinit var btnPlusWeight :FloatingActionButton
    /** Referencia al TextView que muestra el valor actual del peso. */
    private lateinit var tvWeight: TextView

    /** Referencia al botón flotante para disminuir el valor de la edad. */
    private lateinit var btnSubtractAge: FloatingActionButton
    /** Referencia al botón flotante para aumentar el valor de la edad. */
    private lateinit var btnPlusAge :FloatingActionButton
    /** Referencia al TextView que muestra el valor actual de la edad. */
    private lateinit var tvAge: TextView
    /** Referencia al botón que inicia el cálculo del IMC. */
    private lateinit var btnCalculate: Button

    // --- Constantes ---
    /**
     * Un `companion object` es similar a los miembros estáticos en otros lenguajes.
     * Los valores definidos aquí pertenecen a la clase `ImcActivity` en sí, no a una
     * instancia específica de ella.
     */
    companion object{
        /**
         * Clave constante que se utiliza para pasar el resultado del IMC
         * entre `ImcActivity` y `ResultIMCActivity` a través de un Intent.
         * Usar una constante evita errores tipográficos al escribir la clave
         * como un simple String.
         */
        const val IMC_KEY = "IMC_RESULT"
    }
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

        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        tvAge = findViewById(R.id.tvAge)

        btnCalculate = findViewById(R.id.btnCalculate)
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
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }

        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            setWeight()
        }

        btnSubtractWeight.setOnClickListener {
            currentWeight -= 1
            setWeight()
        }

        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }

        btnSubtractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }


        btnCalculate.setOnClickListener{
            val result =calculateIMC()
            navigateToResult(result)
        }
    }

    /**
     * Navega a la actividad `ResultIMCActivity` para mostrar el resultado del cálculo.
     *
     * Este método crea un `Intent` para iniciar la nueva actividad, adjunta el resultado
     * del IMC como un dato "extra" para que la actividad de destino pueda leerlo,
     * y finalmente inicia la transición a `ResultIMCActivity`.
     *
     * @param result El valor del IMC calculado (Double), que se pasará a la siguiente actividad.
     */
    private fun navigateToResult(result: Double) {
        // 1. Crear un Intent: Un 'Intent' es un objeto de mensajería que se puede usar
        //    para solicitar una acción de otro componente de la aplicación.
        //    Aquí, especifica que queremos pasar de la actividad actual (`this`)
        //    a la actividad `ResultIMCActivity`.
        val intent = Intent(this, ResultIMCActivity::class.java)
        // 2. Añadir datos extra al Intent: `putExtra` permite adjuntar datos adicionales
        //    al intent. Estamos "empaquetando" el resultado del IMC.
        //    - `IMC_KEY`: Es una clave constante (String) que actúa como un identificador único
        //      para este dato. La actividad receptora usará esta misma clave para recuperar el valor.
        //    - `result`: Es el valor del IMC que queremos enviar.
        intent.putExtra(IMC_KEY, result)
        // 3. Iniciar la nueva actividad: `startActivity` le dice al sistema Android que
        //    inicie la actividad especificada en el intent, realizando la transición
        //    de pantalla.
        startActivity(intent)
    }

    /**
     * Calcula el Índice de Masa Corporal (IMC) utilizando los valores actuales de peso y altura.
     *
     * La fórmula del IMC es: peso (kg) / [altura (m)]^2.
     * Esta función toma el peso actual (`currentWeight`) y la altura (`currentHeight`),
     * convierte la altura de centímetros a metros, aplica la fórmula y finalmente
     * formatea el resultado para que tenga un máximo de dos decimales.
     *
     * @return El valor del IMC calculado como un `Double` con dos decimales.
     */
    private fun calculateIMC():Double {
        // 1. Crear un formateador de decimales: DecimalFormat("#.##") se usa para
        //    asegurar que el resultado final no tenga más de dos decimales.
        val df = DecimalFormat("#.##")
        // 2. Calcular el IMC:
        //    - currentHeight.toDouble() / 100: Se convierte la altura (que está en cm) a Double
        //      y se divide por 100 para obtener la altura en metros.
        //    - ... * ...: Se multiplica por sí mismo para obtener la altura al cuadrado (altura^2).
        //    - currentWeight / ...: Se divide el peso (en kg) por la altura al cuadrado (en m^2).
        val imc = currentWeight / (currentHeight.toDouble() /100 * currentHeight.toDouble()/100)
        // 3. Formatear y devolver el resultado:
        //    - df.format(imc): Aplica el formato al resultado del IMC, devolviendo un String
        //      (ej: "24.56" o "21.3").
        //    - .toDouble(): Convierte ese String formateado de nuevo a un tipo `Double`
        //      para que pueda ser devuelto por la función y usado en cálculos posteriores
        //      o pasado a la siguiente actividad.
        return df.format(imc).toDouble()
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
        setAge()
    }

    /**
     * Actualiza el TextView que muestra la edad en la interfaz de usuario.
     *
     * Esta función toma el valor actual de la variable `currentAge` (un entero),
     * lo convierte a un String y lo asigna como el texto del `TextView` `tvAge`.
     * Se llama cada vez que el valor de la edad cambia para que el usuario vea
     * la actualización en pantalla.
     */

    private fun setAge(){
        tvAge.text = currentAge.toString()
    }

}