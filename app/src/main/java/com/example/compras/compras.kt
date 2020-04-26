package com.example.compras

import NavigationManager
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_compras.*
import kotlinx.android.synthetic.main.drawer_header.view.*

class compras () : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var listaCompras = ArrayList<lista_compras>()

    var produto1 = lista_compras("Bananas",3, 5.0)
    var produto2 = lista_compras("Cebolas",3, 4.0)
    var produto3 = lista_compras("Batatas",3, 1.0)

    // Calcular # produtos + preços totais

    fun calcularTotal (): String {
        var totalaPagar : Double = 0.0
        var i : Int = 0

        for (item in listaCompras) {
            totalaPagar += (listaCompras[i].quantidade * listaCompras[i].precoUnitario)
            i += 1
        }

        val valorString : String

        valorString = "Total a Pagar: " + totalaPagar + "€"

        return valorString
    }

    fun calcularQuantidades () : String {
        var i : Int = 0
        var quantidade : Int = 0

        for (item in listaCompras) {
            quantidade += listaCompras[i].quantidade
            i += 1
        }

        val quantidadeString = "# Artigos: " + quantidade

        return quantidadeString
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
        val user_nome = intent.getStringExtra("nome_utilizador")
        val nome_user = intent.getStringExtra("utilizador")
        user_nome.let { nav_drawer.getHeaderView(0).nome_utilizador.text = it }
        nome_user.let { nav_drawer.getHeaderView(0).username.text = it }

        if (!screenRotated(savedInstanceState)) {
            NavigationManager.goToComprasFragment(supportFragmentManager)
        }

        // CODIGO PARA CARREGAR LISTA


        // FIM DE CODIGO DE LISTA

        calcularQuantidades().let { nav_drawer.getHeaderView(0).quantidadeArtigos.text = it }
        calcularTotal().let { nav_drawer.getHeaderView(0).valorTotalPagar.text = it }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.criarArtigo -> NavigationManager.goToNovoArtigoFragment(supportFragmentManager)
        }

        menu_drawer.closeDrawer(GravityCompat.START)
        return true
    }




}

