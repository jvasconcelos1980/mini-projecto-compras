package com.example.compras

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_compras.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log
import android.util.*
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    val user1 = users("John Doe","john@ulusofona.pt","doe")
    val user2 = users("Gary Mitch","gary@ulusofona.pt","mitch")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_id.setOnClickListener (){

            var inputUser = findViewById(R.id.username_id) as EditText // ALTERAR FindbyID
            var inputPassword = findViewById(R.id.password_id) as EditText // ALTERAR FindbyID

            val u = inputUser.text.toString()
            val p = inputPassword.text.toString()

            // incluir FOR para validar user

            if ((user1.user.equals(u) && user1.password.equals(p)) || (user2.user.equals(u) && user2.password.equals(p))) {
                val i = Intent(applicationContext, com.example.compras.compras::class.java)
                // intent.apply { i,  }
                startActivity(i)
                Toast.makeText(this,"Great job!",Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this,"User ou password incorrectos.",Toast.LENGTH_LONG).show()
            }
        }

    }

}
