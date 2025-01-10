package com.example.listedespointages

import Joueur
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JoueurAdapter(
    private val joueurs: List<Joueur>,
    private val onClick: (Joueur) -> Unit
) : RecyclerView.Adapter<JoueurAdapter.JoueurViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JoueurViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_joueur, parent, false)
        return JoueurViewHolder(view)
    }

    override fun onBindViewHolder(holder: JoueurViewHolder, position: Int) {
        val joueur = joueurs[position]
        holder.bind(joueur)
        holder.itemView.setOnClickListener { onClick(joueur) }
    }

    override fun getItemCount(): Int = joueurs.size

    class JoueurViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNom: TextView = itemView.findViewById(R.id.tvNom)
        private val tvPointage: TextView = itemView.findViewById(R.id.tvPointage)
        private val imgRank: ImageView = itemView.findViewById(R.id.imgRank)

        fun bind(joueur: Joueur) {
            tvNom.text = joueur.nom
            tvPointage.text = "Pointage: ${joueur.pointage}"

            val rankDrawable = when (joueur.pointage) {
                in 0..49 -> R.drawable.rank1
                in 50..99 -> R.drawable.rank2
                else -> R.drawable.rank3
            }
            imgRank.setImageResource(rankDrawable)
        }
    }
}
