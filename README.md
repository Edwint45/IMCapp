# IMCapp

IMCapp es una aplicación desarrollada principalmente en **Kotlin** para Android, cuyo propósito es calcular el Índice de Masa Corporal (IMC) de los usuarios. El IMC es una métrica utilizada para determinar si una persona tiene un peso saludable en relación con su altura.

## Estructura General del Proyecto

- **Lenguaje Principal:** Kotlin
- **Repositorio:** [Edwint45/IMCapp](https://github.com/Edwint45/IMCapp)
- **Branch principal:** `master`
- **Visibilidad:** Público
- **Licencia:** No especificada

## ¿Cómo Funciona IMCapp?

IMCapp permite a los usuarios ingresar su peso y altura, y calcula automáticamente el IMC usando la fórmula estándar:
```
IMC = Peso (kg) / [Altura (m)]^2
```
Según el valor calculado, la app muestra una interpretación (bajo peso, peso normal, sobrepeso, obesidad).

### Flujo Básico de Uso

1. **Inicio:** El usuario abre la aplicación en Android.
2. **Entrada de Datos:** Se solicita peso y altura.
3. **Cálculo:** Se procesa la información y se calcula el IMC.
4. **Resultados:** Se muestra el resultado y la interpretación correspondiente.

## Estructura de Carpetas y Archivos

La organización típica de un proyecto Android incluye:

- `/app/src/main/java/`: Código fuente principal en Kotlin.
- `/app/src/main/res/layout/`: Archivos de diseño (layouts) de la interfaz de usuario.
- `/app/src/main/res/`: Recursos, imágenes, cadenas, estilos, etc.
- `/app/build.gradle`: Configuración del módulo de la app.
- `/build.gradle`: Configuración global del proyecto.
- `AndroidManifest.xml`: Declaraciones principales de la app.

## Componentes Principales

- **Actividad Principal (`MainActivity`):** Gestiona la UI y la lógica del cálculo del IMC.
- **Layouts XML:** Define la distribución visual y los elementos de la interfaz para la entrada de datos y visualización de resultados.
- **Lógica de Cálculo:** Implementada en Kotlin, encapsula la fórmula y la interpretación.

## Estructura y Explicación de los Layouts

Los layouts en Android definen la apariencia y estructura de la interfaz de usuario. En IMCapp suelen ubicarse en `/app/src/main/res/layout/` y están escritos en XML. Aunque el contenido específico no está disponible, por la naturaleza de una app de IMC se espera lo siguiente:

### Layout Principal

- **Componentes:**
  - **EditText** para ingreso de peso.
  - **EditText** para ingreso de altura.
  - **Button** para calcular el IMC.
  - **TextView** para mostrar el resultado del IMC.
  - **TextView** para mostrar la interpretación del IMC.

- **Estructura típica:**
  - Un **LinearLayout** o **ConstraintLayout** como contenedor principal.
  - Los campos de entrada alineados verticalmente para facilitar la interacción.
  - Botón de acción centrado y de fácil acceso.
  - Área de resultados visible tras el cálculo.

### Ejemplo de Layout XML (referencial)

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <EditText
        android:id="@+id/editPeso"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Peso (kg)"
        android:inputType="numberDecimal"/>

    <EditText
        android:id="@+id/editAltura"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Altura (m)"
        android:inputType="numberDecimal"/>

    <Button
        android:id="@+id/btnCalcular"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Calcular IMC"
        android:layout_gravity="center"/>

    <TextView
        android:id="@+id/txtResultado"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Resultado:"
        android:textSize="18sp"
        android:visibility="gone"/>
</LinearLayout>
```

### Comportamiento

- Al presionar el botón, la app toma los valores de los EditText, calcula el IMC y actualiza el TextView con el resultado y la interpretación.
- El layout puede contener validaciones visuales para asegurarse de que los campos estén completos y sean válidos.

## Documentación Técnica

### Ejemplo de cálculo en Kotlin

```kotlin
fun calcularIMC(peso: Double, altura: Double): Double {
    return peso / (altura * altura)
}
```

### Interpretación del resultado

```kotlin
fun interpretarIMC(imc: Double): String {
    return when {
        imc < 18.5 -> "Bajo peso"
        imc < 25.0 -> "Peso normal"
        imc < 30.0 -> "Sobrepeso"
        else -> "Obesidad"
    }
}
```

## Cómo contribuir

1. Clona el repositorio:
   ```
   git clone https://github.com/Edwint45/IMCapp.git
   ```
2. Instala Android Studio y abre el proyecto.
3. Realiza cambios, agrega nuevas funciones o mejora la UI.
4. Haz un pull request explicando tus cambios.

## Estado del Proyecto

- **Activo:** No está archivado y está en el branch `master`.
- **Colaboradores:** Actualmente no registra forks ni estrellas, pero es abierto a contribuciones.

## Recursos Adicionales

- [Guía oficial de Android en Kotlin](https://developer.android.com/kotlin)
- [Documentación sobre IMC](https://es.wikipedia.org/wiki/%C3%8Dndice_de_masa_corporal)

---

**Nota:** Esta documentación está basada en la estructura típica de apps Android y en los metadatos públicos del repositorio. Si necesitas detalles específicos de los archivos XML de layouts, se recomienda revisar directamente la carpeta `/app/src/main/res/layout/` en el repositorio.
