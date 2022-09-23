package com.richard.adivinhaquem.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.Slider
import com.richard.adivinhaquem.Palavras
import com.richard.adivinhaquem.R
import com.richard.adivinhaquem.adapter.JogadorNovoAdapter
import com.richard.adivinhaquem.ext.MyLinearLayoutManager

class Iniciar : AppCompatActivity() {
    lateinit var listaRecylerJogadores : RecyclerView;
    lateinit var sliderPalavras: Slider
    lateinit var sliderTempo: Slider
    val jogadores = ArrayList<String>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar)

        listaRecylerJogadores = findViewById(R.id.iniciar_lista_jogadores)
        listaRecylerJogadores.layoutManager = LinearLayoutManager(this)
        listaRecylerJogadores.adapter = JogadorNovoAdapter(jogadores, listaRecylerJogadores, this.resources.displayMetrics.density)

        sliderPalavras = findViewById(R.id.iniciar_slider_palavras)
        sliderTempo = findViewById(R.id.iniciar_slider_tempo)
    }

    fun modalAdicionarPessoas(v: View){
        val dialog = AlertDialog.Builder(this).create();

        val view = layoutInflater.inflate(R.layout.cadastrar_jogador, null);

        view.findViewById<Button>(R.id.view_cadastrar_jogador_botao).setOnClickListener {
            novaPessoa(view.findViewById<EditText>(R.id.view_cadastrar_jogador_edit_text).text.toString())
            dialog.dismiss()
        }

        dialog.setView(view);

        dialog.show()
    }

    fun novaPessoa(nome: String){
        if(jogadores.isEmpty()){
            listaRecylerJogadores.visibility = View.VISIBLE;
            findViewById<View>(R.id.iniciar_text_ninguem).visibility = View.GONE;
        }

        jogadores.add(nome)

        listaRecylerJogadores.updateLayoutParams<ViewGroup.LayoutParams> {
            height = (this@Iniciar.resources.displayMetrics.density * jogadores.size * 22).toInt()
        }
    }

    fun iniciar(v: View){
        val intent = Intent(this, Jogo::class.java)

        intent.putStringArrayListExtra("jogadores", jogadores)
        intent.putExtra("palavras", sliderPalavras.value.toInt())
        intent.putExtra("tempo", sliderTempo.value.toInt())

        startActivity(intent)
        finish()
    }

}