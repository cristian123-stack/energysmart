package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    // Configuración de ViewBinding
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicialización de Firebase Auth
        auth = FirebaseAuth.getInstance()

        binding.botoniniciar.setOnClickListener {
            val email = binding.inputUsuario.text.toString()
            val contraseña = binding.inputContrasena.text.toString()

            if (email.isEmpty()) {
                binding.inputUsuario.error = "Por favor ingrese un email"
                return@setOnClickListener
            }

            if (contraseña.isEmpty()) {
                binding.inputContrasena.error = "Por favor ingrese una contraseña"
                return@setOnClickListener
            }

            signIn(email, contraseña)
        }
    }

    private fun signIn(email: String, contraseña: String) {
        auth.signInWithEmailAndPassword(email, contraseña)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, dashboard::class.java)
                    startActivity(intent)

                    // Aquí puedes manejar el éxito del inicio de sesión, por ejemplo, navegación a otra actividad
                } else {
                    Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                    // Aquí puedes manejar el error del inicio de sesión, como mostrar un mensaje de error al usuario
                }
            }
    }
}
