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

    // criação de 2 users
    val user1 = users("John Doe","john@ulusofona.pt","doe")
    val user2 = users("Gary Mitch","gary@ulusofona.pt","mitch")

    private var lista_users = ArrayList<users>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // adicionar user1 e users 2 na lista de users

        lista_users.add(user1)
        lista_users.add(user2)

        login_id.setOnClickListener (){

            //variaveis de inputs no quadro de auth
            val username = username_id.text.toString()
            val password = password_id.text.toString()

            // procurar se user é valido dentro da lista de utilizadores

            for (user in lista_users) {
                if (user.getUsername() == username && user.getPwd() == password) {
                //auth valida
                    val intentLogin = Intent(this,compras::class.java)
                    intentLogin.apply { putExtra("utilizador", user.getUsername()) }
                    intentLogin.apply { putExtra("nome_utilizador", user.getNomeUser()) }
                    startActivity(intentLogin)
                    Toast.makeText(this,"Great job!",Toast.LENGTH_LONG).show()
                } else {
                    // auth não valida
                    Toast.makeText(this,"User ou Password incorreto. Tente novamente.",Toast.LENGTH_LONG).show()
                }
            }

        }

    }

}
