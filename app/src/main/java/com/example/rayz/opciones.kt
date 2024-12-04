package com.example.rayz

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rayz.databinding.ActivityOpcionesBinding

class opciones : AppCompatActivity() {

    private lateinit var binding: ActivityOpcionesBinding

    // Toggle para el DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar binding
        binding = ActivityOpcionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

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

        // Configurar clics en el NavigationView
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, PostLogin::class.java)
                    startActivity(intent)
                }
                R.id.nav_opciones -> {
                    val intent = Intent(this, opciones123::class.java)
                    startActivity(intent)
                }
                R.id.nav_gestion -> {
                    val intent = Intent(this, Gestion::class.java)
                    startActivity(intent)
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true

        }
        val btnEncender: Button = findViewById(R.id.buttonEncender)  // Botón para encender la luz.
        val btnApagar: Button = findViewById(R.id.buttonApagar)  // Botón para apagar la luz




        // Ajustar padding para barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Asignar un evento al botón de encender.
        btnEncender.setOnClickListener {
            Toast.makeText(this, "La luz ha sido encendida", Toast.LENGTH_SHORT).show()
        }

        // Asignar un evento al botón de apagar.
        btnApagar.setOnClickListener {
            Toast.makeText(this, "La luz ha sido apagada", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
