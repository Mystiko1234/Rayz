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
import com.example.rayz.databinding.ActivityPostLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class PostLogin : AppCompatActivity() {

    // Configuración de viewBinding
    private lateinit var binding: ActivityPostLoginBinding

    // Configuración de Firebase
    private lateinit var auth: FirebaseAuth

    // Toggle para el DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPostLoginBinding.inflate(layoutInflater)
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

        // Inicializar Firebase
        auth = Firebase.auth

        // Configurar clics en el NavigationView
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_opciones -> {
                    val intent = Intent(this, opciones123::class.java)
                    startActivity(intent)
                }
                R.id.nav_controlManual -> {
                    val intent = Intent(this, opciones::class.java)
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
        val btnSeleccionarDispositivo: Button = findViewById(R.id.btn_seleccionar_dispositivo)
        btnSeleccionarDispositivo.setOnClickListener {
            Toast.makeText(this, "Se seleccionó un dispositivo", Toast.LENGTH_SHORT).show()
        }

        // Inicializa el botón para conectar un dispositivo
        val btnConectar: Button = findViewById(R.id.btn_conectar)
        btnConectar.setOnClickListener {
            Toast.makeText(this, "Se conectó un dispositivo", Toast.LENGTH_SHORT).show()
        }

        // Inicializa el botón para desconectar un dispositivo
        val btnDesconectar: Button = findViewById(R.id.btn_desconectar)
        btnDesconectar.setOnClickListener {
            Toast.makeText(this, "Se desconectó el dispositivo", Toast.LENGTH_SHORT).show()
        }

        // Configurar botón de logout
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Cerrar sesión")
                .setMessage("¿Estás seguro de que deseas cerrar sesión?")
                .setNeutralButton("Cancelar") { _, _ -> }
                .setPositiveButton("Aceptar") { _, _ -> signOut() }
                .show()
        }

        // Ajustar padding para las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signOut() {
        Firebase.auth.signOut()
        // Redirigir a la pantalla de inicio de sesión
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish() // Cierra la actividad actual
        Toast.makeText(this, "Se Cerro Sesión correctamente", Toast.LENGTH_SHORT).show()
    }
}
