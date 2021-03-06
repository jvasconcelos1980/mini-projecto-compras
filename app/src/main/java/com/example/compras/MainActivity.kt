package com.example.compras

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    // criação de 2 users
    val user1 = Utilizadores("John Doe","john@ulusofona.pt","doe")
    val user2 = Utilizadores("Gary Mitch","gary@ulusofona.pt","mitch")

    var login_ok : Boolean = false

    private var lista_users = ArrayList<Utilizadores>()

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
                if (user.obterUsername() == username && user.obterPassword() == password) {
                //auth valida
                    login_ok = true
                    val intentLogin = Intent(this,ListaCompras::class.java)
                    intentLogin.apply { putExtra("utilizador", user.obterUsername()) }
                    intentLogin.apply { putExtra("nome_utilizador", user.obterNome()) }
                    startActivity(intentLogin)
                    Toast.makeText(this,"Great job!",Toast.LENGTH_LONG).show()
                }
                if (!login_ok) {
                    // auth não valida
                    Toast.makeText(this,"User ou Password incorreto. Tente novamente.",Toast.LENGTH_LONG).show()
                }
                                    }
            }

        }

}

