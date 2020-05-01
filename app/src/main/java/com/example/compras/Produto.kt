package com.example.compras

import android.media.Image

class Produto(var nomeProduto: String, var quantidade: Int, var precoUnitario: Double, var adquirido : Boolean = false) {

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

    fun validarAquisicao(){
        this.adquirido = true
    }

    fun obterValidacao() : Boolean {
     return this.adquirido
    }


}