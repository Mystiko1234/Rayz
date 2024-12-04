package com.example.rayz

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.rayz.databinding.ActivityOpciones123Binding

class opciones123 : AppCompatActivity() {

    // Variable para almacenar el color seleccionado por el usuario
    private var selectedColor: String? = null

    private lateinit var binding: ActivityOpciones123Binding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar el binding
        binding = ActivityOpciones123Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar Toolbar
        setSupportActionBar(binding.toolbar)

        // Inicializar el DrawerLayout y su Toggle
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Habilitar botón de menú en el ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Asignar click listener a los botones de color
        findViewById<View>(R.id.color_rojo).setOnClickListener { selectColor("#FF0000", "rojo") }
        findViewById<View>(R.id.color_verde).setOnClickListener { selectColor("#00FF00", "verde") }
        findViewById<View>(R.id.color_azul).setOnClickListener { selectColor("#0000FF", "azul") }
        findViewById<View>(R.id.color_amarillo).setOnClickListener { selectColor("#FFFF00", "amarillo") }
        findViewById<View>(R.id.color_cian).setOnClickListener { selectColor("#00FFFF", "cian") }

        // Asignar click listener al botón para aplicar el color seleccionado
        findViewById<Button>(R.id.btnaplicar_color).setOnClickListener {
            aplicarColor()
        }


        // Configurar clics en el NavigationView
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, PostLogin::class.java)
                    startActivity(intent)
                }
                R.id.nav_opciones -> {
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.nav_controlManual -> {
                    val intent = Intent(this, opciones::class.java)
                    startActivity(intent)
                }
                R.id.nav_controlManual -> {
                    val intent = Intent(this, Gestion::class.java)
                    startActivity(intent)
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true


        }
        val seekBarBrillo = findViewById<SeekBar>(R.id.btnBrillo)
        seekBarBrillo.max = 100 // Establecer el rango del SeekBar
        seekBarBrillo.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Mostrar un mensaje de confirmación al dejar de ajustar el SeekBar
                Toast.makeText(this@opciones123, "Se cambió correctamente el brillo", Toast.LENGTH_SHORT).show()
            }

        })

        val seekBarTemporizador = findViewById<SeekBar>(R.id.Seektemporizador)
        seekBarTemporizador.max = 100 // Establecer el rango del SeekBar
        seekBarTemporizador.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Mostrar un mensaje de confirmación al dejar de ajustar el SeekBar
                Toast.makeText(this@opciones123, "Temporizador ajustado a ${seekBar?.progress}", Toast.LENGTH_SHORT).show()
            }
        })


    }
    // Método para seleccionar un color
    private fun selectColor(color: String, colorName: String) {
        selectedColor = color // Asigna el color seleccionado
        Toast.makeText(this, "Color seleccionado: $colorName", Toast.LENGTH_SHORT).show() // Muestra el color seleccionado
    }
    // Método para aplicar el color seleccionado
    private fun aplicarColor() {
        // Determina el mensaje basado en el color seleccionado
        val colorMessage = when (selectedColor) {
            "#FF0000" -> "Se ha cambiado a rojo"
            "#00FF00" -> "Se ha cambiado a verde"
            "#0000FF" -> "Se ha cambiado a azul"
            "#FFFF00" -> "Se ha cambiado a amarillo"
            "#00FFFF" -> "Se ha cambiado a cian"
            else -> "No se ha seleccionado ningún color"
        }
        // Muestra el mensaje cuando se aplica el color
        Toast.makeText(this, colorMessage, Toast.LENGTH_SHORT).show()
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
