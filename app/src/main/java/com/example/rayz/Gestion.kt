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
import com.example.rayz.databinding.ActivityGestionBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Gestion : AppCompatActivity() {

    private lateinit var binding: ActivityGestionBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar binding
        binding = ActivityGestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

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
                R.id.nav_controlManual -> {
                    val intent = Intent(this, opciones::class.java)
                    startActivity(intent)
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Ajustar padding para barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar acción para editar datos
        binding.btnEditarDatos.setOnClickListener {
            val intent = Intent(this, EditarDatosActivity::class.java)
            startActivity(intent)
        }

        // Configurar acción para eliminar cuenta
        binding.btnEliminarCuenta.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Eliminar cuenta")
                .setMessage("¿Estás seguro de que deseas eliminar tu cuenta? Esta acción no se puede deshacer.")
                .setNeutralButton("Cancelar") { _, _ ->
                    // No se realiza ninguna acción
                }
                .setPositiveButton("Eliminar") { _, _ ->
                    eliminarCuenta()
                }
                .show()
        }

    }

    private fun eliminarCuenta() {
        val user: FirebaseUser? = auth.currentUser
        user?.let {
            it.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Si la eliminación fue exitosa, redirigir a la pantalla de login
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()  // Finaliza la actividad actual
                    } else {
                        // Si hubo un error al eliminar la cuenta, muestra un mensaje
                        showToast("Error al eliminar la cuenta")
                    }
                }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
