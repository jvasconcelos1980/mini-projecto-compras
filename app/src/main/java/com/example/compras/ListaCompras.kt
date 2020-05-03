package com.example.compras
import NavigationManager
import NavigationManager.Companion.goToNovoArtigoFragment
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_compras.*
import kotlinx.android.synthetic.main.drawer_header.view.*
import kotlin.math.log

class ListaCompras () : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var listaCompras = ArrayList<Produto>()
    private var listaComprasDone = ArrayList<Produto>()
    private val TAG = ListaCompras::class.java.simpleName

    // produtos standard na lista
    var produto1 = Produto("Bananas",3, 5.0,adquirido = false)
    var produto2 = Produto("Cebolas",3, 4.0,adquirido = true)
    var produto3 = Produto("Batatas",3, 1.0,adquirido = false)

    // Calcular # produtos + preços totais, etc

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
    fun apagarListaProdutos() {
        listaCompras.clear()
    }
    fun atualizaListaDone () {

        var listaComprasAux = ArrayList<Produto>()

        for (i in 0 until listaCompras.size) {
            val item = listaCompras[i]
            if (item.obterValidacao().equals(true)) {
                listaComprasAux.add(item)
                Log.i(TAG,"Artigo na Lista de adquiridos -> " + item.mostraNomeProduto())
            }
        }
        listaComprasDone = listaComprasAux
    }

    // sem invocação (com erro)
    fun apagarProdutodaLista(produto: Produto) {
            listaCompras.remove(produto)
        }

    // para debug
    fun tamanhoListas(){
        Log.i(TAG,"Lista de artigos adquiridos -> " + listaComprasDone.size)
        Log.i(TAG,"Lista de COMPRAS por adquirir -> " + listaCompras.size)
    }

    // para debug
    fun obterProdutoIndex (item: Int) {
        Log.i(TAG,"Lista de COMPRAS por adquirir -> " + listaCompras[item])
    }

    // Adicionar um novo produto | método não implementado
    fun adicionarProduto (nome: String, qt: Int, valorUnit: Double) {

        var produtoAdicionar = Produto (nome,qt,valorUnit)
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
        // menu & Toolbar
        // adicionar artigos

        listaCompras.add(produto1)
        listaCompras.add(produto2)
        listaCompras.add(produto3)
        tamanhoListas()
        atualizaListaDone()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compras)
        setSupportActionBar(toolbar)
        setupDrawerMenu()

        // update dados de utilizador drawer menu
        val user_nome = intent.getStringExtra("nome_utilizador")
        val nome_user = intent.getStringExtra("utilizador")
        user_nome.let { nav_drawer.getHeaderView(0).nome_utilizador.text = it }
        nome_user.let { nav_drawer.getHeaderView(0).username.text = it }
        tamanhoListas()
        atualizaListaDone()

        if (!screenRotated(savedInstanceState)) {
            NavigationManager.goToComprasFragment(supportFragmentManager)
        }

        // Código para fazer Lista

        this.lista_artigos?.layoutManager = LinearLayoutManager(this)
        lista_artigos?.adapter = ComprasAdapter(this,R.layout.item_expression, listaCompras,ListaCompras())
        calculateTotals()
        tamanhoListas()
        atualizaListaDone()

        let {
            (lista_artigos?.adapter as ComprasAdapter).registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onChanged() {
                    Toast.makeText(it,"A recalcular totais...",Toast.LENGTH_LONG).show()
                    calculateTotals()
                    tamanhoListas()
                }
            })
        }

}
override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.adicionar_artigo -> {
                goToNovoArtigoFragment(supportFragmentManager)
            }
            R.id.id_apagar_tudo_lista -> {
                Toast.makeText(this,"clicou em apagar tudo",Toast.LENGTH_LONG).show()
                apagarListaProdutos()
                atualizaListaDone()
                calculateTotals()
                tamanhoListas()
                lista_artigos?.adapter = ComprasAdapter(this,R.layout.item_expression, listaCompras, ListaCompras())
            }
            R.id.id_produtos_adquiridos -> {
                atualizaListaDone()
                tamanhoListas()
                lista_artigos?.adapter = ComprasAdapterDone(this,R.layout.item_expression_done, listaComprasDone)
            }
            R.id.id_to_do_list -> lista_artigos?.adapter = ComprasAdapter(this,R.layout.item_expression, listaCompras, ListaCompras())
        }
        return true
    }

    private fun calculateTotals(){
        calcularQuantidades().let { nav_drawer.getHeaderView(0).quantidadeArtigos.text = it }
        calcularTotal().let { nav_drawer.getHeaderView(0).valorTotalPagar.text = it }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.drawer_logout ->  {
                setContentView(R.layout.activity_main)
            }
        }
        menu_drawer.closeDrawer(GravityCompat.START)
        Log.i(TAG,"menu fechado...")
        return true
    }

}

