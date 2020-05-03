package com.example.compras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_formulario_compras.*

class FormularioComprasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_compras)

        cancelar_button.setOnClickListener {
            val intent = Intent(this,ListaCompras::class.java)
            startActivity(intent)
        }
    }
}
