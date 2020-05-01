package com.example.compras

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_expression.view.*


class ComprasAdapter(private val context: Context, private val layout: Int,
                     private val items: List<Produto>) : RecyclerView.Adapter<ComprasAdapter.ComprasViewHolder>()  {
    class ComprasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val produto: TextView = view.text_produto
        val quantidade: TextView = view.text_quantidade
        val precoUnit: TextView = view.text_precoUnit
        val total: TextView = view.text_total_pagar
        val adquirido: TextView = view.texto_done_or_not_done
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComprasViewHolder {
        return ComprasViewHolder(LayoutInflater.from(context).inflate(layout,parent,false))
    }

    override fun onBindViewHolder(holder: ComprasViewHolder, position: Int) {
        holder.produto.text = items[position].mostraNomeProduto()
        holder.quantidade.text = items[position].mostraQuantidade().toString()
        holder.precoUnit.text = items[position].mostraPreco().toString()
        holder.total.text = items[position].calcularPrecos().toString()

        if (items[position].obterValidacao() == false) {
            holder.adquirido.text = "Por adquirir"
        } else {
            holder.adquirido.text = "ADQUIRIDO"
        }
    }


    fun aumentaQuantidade (position: Int) {
        items[position].aumentarQt()
    }

    fun diminiuQuantidade (position: Int) {
        items[position].diminuirQt()
    }

    fun alterarEstado (position: Int) {
        items[position].validarAquisicao()
    }

    override fun getItemCount() = items.size

}