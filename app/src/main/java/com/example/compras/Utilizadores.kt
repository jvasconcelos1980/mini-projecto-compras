package com.example.compras

class Utilizadores (var nome: String, var user: String, var password: String){

    fun obterUsername() : String {
        return this.user
    }

    fun obterPassword() : String {
        return this.password
    }

    fun obterNome() : String {
        return this.nome
    }
}




