package com.example.rayz

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class EditarDatosActivity : AppCompatActivity() {

    private lateinit var edtContrasena: EditText
    private lateinit var btnGuardar: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_datos)

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Referencias a los campos de texto
        edtContrasena = findViewById(R.id.edtContraseña)
        btnGuardar = findViewById(R.id.btnGuardar)

        // Configurar el botón de guardar
        btnGuardar.setOnClickListener {
            guardarDatos()
        }
    }

    // Guardar los nuevos datos del usuario en Firebase Authentication
    private fun guardarDatos() {
        val nuevaContrasena = edtContrasena.text.toString().trim()

        val user: FirebaseUser? = auth.currentUser
        user?.let {
            // Si el usuario ha ingresado una nueva contraseña, actualizarla
            if (nuevaContrasena.isNotEmpty()) {
                it.updatePassword(nuevaContrasena)
                    .addOnCompleteListener { passwordTask ->
                        if (passwordTask.isSuccessful) {
                            Toast.makeText(this, "Contraseña actualizada correctamente", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, Gestion::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Error al actualizar la contraseña: ${passwordTask.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

            // Si no se ha ingresado una nueva contraseña, mostrar un mensaje
            if (nuevaContrasena.isEmpty()) {
                Toast.makeText(this, "No se ha realizado ningún cambio en la contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
