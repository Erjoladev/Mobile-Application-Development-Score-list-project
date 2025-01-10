package com.example.listedespointages

import Joueur
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//class DetailActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)
//
//        val joueur = intent.getParcelableExtra<Joueur>("joueur") ?: return
//
//        val tvNom: TextView = findViewById(R.id.tvNom)
//        val tvPointage: TextView = findViewById(R.id.tvPointage)
//        val btnAjouter1: Button = findViewById(R.id.btnAjouter1)
//        val btnAjouter10: Button = findViewById(R.id.btnAjouter10)
//        val btnRetour: Button = findViewById(R.id.btnRetour)
//
//        tvNom.text = joueur.nom
//        tvPointage.text = joueur.pointage.toString()
//
//        btnAjouter1.setOnClickListener {
//            joueur.pointage += 1
//            tvPointage.text = joueur.pointage.toString()
//        }
//
//        btnAjouter10.setOnClickListener {
//            joueur.pointage += 10
//            tvPointage.text = joueur.pointage.toString()
//        }
//
//        btnRetour.setOnClickListener {
//            val intent = Intent().apply {
//                putExtra("updated_joueur", joueur)
//            }
//            setResult(RESULT_OK, intent)
//            finish()
//        }
//    }
//}


class DetailActivity : AppCompatActivity() {

    private lateinit var joueur: Joueur

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Retrieve the Joueur object from the intent
        joueur = intent.getParcelableExtra("joueur")
            ?: run {
                Toast.makeText(this, "Erreur lors du chargement des données", Toast.LENGTH_SHORT).show()
                finish() // Close the activity if data is missing
                return
            }

        // Find views by their IDs
        val imgRank: ImageView = findViewById(R.id.imgRank)
        val tvNom: TextView = findViewById(R.id.tvNom)
        val tvPointage: TextView = findViewById(R.id.tvPointage)
        val btnAjouter1: Button = findViewById(R.id.btnAjouter1)
        val btnAjouter10: Button = findViewById(R.id.btnAjouter10)
        val btnRetour: Button = findViewById(R.id.btnRetour)

        // Set initial player details
        tvNom.text = joueur.nom
        tvPointage.text = joueur.pointage.toString()

        // Handle adding 1 point
        btnAjouter1.setOnClickListener {
            joueur.pointage += 1
            tvPointage.text = joueur.pointage.toString()
        }

        // Handle adding 10 points
        btnAjouter10.setOnClickListener {
            joueur.pointage += 10
            tvPointage.text = joueur.pointage.toString()
        }

        // Handle return to MainActivity
        btnRetour.setOnClickListener {
            val intent = Intent().apply {
                putExtra("updated_joueur", joueur)
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
