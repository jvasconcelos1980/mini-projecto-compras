package com.example.compras

import NavigationManager
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_compras.*

class compras () : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var listaCompras = ArrayList<lista_compras>()

    var produto1 = lista_compras("Bananas",3, 5.0)
    var produto2 = lista_compras("Cebolas",3, 4.0)
    var produto3 = lista_compras("Batatas",3, 1.0)

    // Calcular # produtos + preços totais

    fun calcularTotal (): Double {
        var totalaPagar : Double = 0.0
        var i : Int = 0

        for (item in listaCompras) {
            totalaPagar += (listaCompras[i].quantidade * listaCompras[i].precoUnitario)
            i += 1
        }

        return totalaPagar
    }

    fun calcularQuantidades () : Int {
        var i : Int = 0
        var quantidade : Int = 0

        for (item in listaCompras) {
            quantidade += listaCompras[i].quantidade
            i += 1
        }

        return quantidade
    }

    // Adicionar um novo produto

    fun adicionarProduto (nome: String, qt: Int, valorUnit: Double) {

        var produtoAdicionar = lista_compras (nome,qt,valorUnit)
        listaCompras.add(produtoAdicionar)
        Toast.makeText(this,"Foi adicionado o artigo $nome à lista de compras", Toast.LENGTH_LONG).show()

    }

    private fun screenRotated(savedInstanceState: Bundle?): Boolean {
        return savedInstanceState != null
    }

    override fun onBackPressed() {
        if(menu_drawer.isDrawerOpen(GravityCompat.START))
            menu_drawer.closeDrawer(GravityCompat.START)
        else if(supportFragmentManager.backStackEntryCount == 1) finish()
        else super.onBackPressed()
    }

    private fun setupDrawerMenu() {
        val toggle =
            ActionBarDrawerToggle(
                this,
                menu_drawer,
                toolbar,
                R.string.open_menu,
                R.string.close_menu
            )
        nav_drawer.setNavigationItemSelectedListener(this)
        menu_drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        listaCompras.add(produto1)
        listaCompras.add(produto2)
        listaCompras.add(produto3)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compras)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
        if (!screenRotated(savedInstanceState)) {
            NavigationManager.goToCalculatorFragment(supportFragmentManager)
        }


        // CODIGO PARA CARREGAR LISTA



        // FIM DE CODIGO DE LISTA


        val t = calcularTotal()
        val q = calcularQuantidades()

        Toast.makeText(this,"Totais Atualizados...", Toast.LENGTH_SHORT).show()
        // Incluir IDs dos textos

        // crashing - java.lang.IllegalStateException: quantidadeArtigos must not be null
        // resolver mais tarde (info encontra-se no menu drawer

        //valorTotalPagar.text = t.toString()
        //quantidadeArtigos.text = "# Artigos: " + q.toString()

    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return true
    }
}

