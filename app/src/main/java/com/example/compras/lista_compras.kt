package com.example.compras

import android.media.Image

class lista_compras(val nomeProduto: String, val quantidade: Int, val precoUnitario: Double) {

    fun getProduto() : String {
        return this.nomeProduto
    }

    fun getQuantidades() : Int {
        return this.quantidade
    }

    fun getPrecos() : Double {

        return this.precoUnitario
    }

}