package com.example.compras

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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
        val botao_add: TextView = view.botao_add
        val botao_remove: TextView = view.botao_remove
        val botaoConfirmarDone: TextView = view.botao_confirmar_done
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComprasViewHolder {
        return ComprasViewHolder(LayoutInflater.from(context).inflate(layout,parent,false))
    }

    override fun onBindViewHolder(holder: ComprasViewHolder, position: Int) {
        holder.produto.text = items[position].mostraNomeProduto()
        holder.quantidade.text = items[position].mostraQuantidade().toString()
        holder.precoUnit.text = items[position].mostraPreco().toString()
        holder.total.text = items[position].calcularPrecos().toString()

        if (!items[position].obterValidacao()) {
            holder.adquirido.text = "Por adquirir"
        } else {
            holder.adquirido.text = "ADQUIRIDO"
        }
        holder.botao_add.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"Mais um artigo...", Toast.LENGTH_LONG).show();
            items[position].quantidade++
            notifyDataSetChanged()
        })


        holder.botao_remove.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"Menos um artigo...",Toast.LENGTH_LONG).show()
            if(items[position].quantidade > 1 ){
                items[position].quantidade--
            }else{
                Toast.makeText(context,"Impossível números negativos! ",Toast.LENGTH_LONG).show()
            }
            notifyDataSetChanged()
        })

        holder.botaoConfirmarDone.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,"Validado",Toast.LENGTH_LONG).show()
            if(!items[position].obterValidacao()){
                items[position].validarAquisicao()
            }
            notifyDataSetChanged()
        })

    }


    override fun getItemCount() = items.size
}