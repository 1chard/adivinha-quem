package com.richard.adivinhaquem.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.richard.adivinhaquem.R


class JogadorNovoAdapter(private val jogadores : List<String>, private val recyclerView: RecyclerView, private val density : Float) : RecyclerView.Adapter<JogadorNovoAdapter.JogadorNovoViewHolder>() {
    class JogadorNovoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val textViewMain : TextView;

        init {
            textViewMain = itemView.findViewById(R.id.view_jogador_novo_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogadorNovoViewHolder {



        val v: View =
            LayoutInflater.from(parent.getContext())
                .inflate(
                    R.layout.jogador_novo,
                    parent,
                    false
                )




        return JogadorNovoViewHolder(v)
    }

    override fun onBindViewHolder(holder: JogadorNovoViewHolder, position: Int) {
        holder.textViewMain.text = jogadores[position]

        if(position % 2 == 0){
            holder.itemView.setBackgroundColor(0xFFAAAAAA.toInt())
        }
    }

    override fun getItemCount(): Int {
        return jogadores.size;
    }
}