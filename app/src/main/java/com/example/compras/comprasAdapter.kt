package com.example.compras

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.drawer_header.*

class comprasAdapter (private val context: Context,
                               private val dataSource: ArrayList<lista_compras>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.activity_compras, parent, false)
        val titleTextView = rowView.findViewById(R.id.lista_artigos) as ListView
        val subtitleTextView = rowView.findViewById(R.id.lista_artigos) as TextView
        val detailTextView = rowView.findViewById(R.id.lista_artigos) as TextView
        //val thumbnailImageView = rowView.findViewById(R.id.lista_artigos) as ImageView

        val artigoLista = getItem(position) as lista_compras
        titleTextView.text = artigoLista.nomeProduto
        subtitleTextView.text = artigoLista.quantidade.toString()
        detailTextView.text = artigoLista.precoUnitario.toString()

        //Picasso.with(context).load(artigoLista.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView)

        return rowView
    }


}
