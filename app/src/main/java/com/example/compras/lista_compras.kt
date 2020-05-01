package com.example.compras

import android.media.Image

class lista_compras(val nomeProduto: String, val quantidade: Int, val precoUnitario: Double) {

    fun mostraNomeProduto() : String {
        return this.nomeProduto
    }

    fun mostraQuantidade() : Int {
        return this.quantidade
    }

    fun mostraPreco() : Double {
        return this.precoUnitario
    }

    fun calcularPrecos() : Double {
        return this.precoUnitario * this.quantidade
    }

    // para implementar onclick no item selecionado
    fun aumentarQt() {
        this.quantidade.inc()
    }

    // para implementar onclick no item selecionado
    fun diminuirQt() {
        this.quantidade.dec()
    }

}