package com.example.listedespointages

import Joueur
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var joueursAdapter: JoueurAdapter
    private lateinit var joueurs: ArrayList<Joueur>
    private val DETAIL_REQUEST_CODE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val editNom: EditText = findViewById(R.id.editTextNom)
        val btnAjouter: Button = findViewById(R.id.buttonAjouter)

        // Load players from file
        joueurs = loadPlayersFromFile(this)

        // Sort players by score (descending order)
        joueurs.sortByDescending { it.pointage }

        joueursAdapter = JoueurAdapter(joueurs) { joueur ->
            openDetailActivity(joueur)
        }

        recyclerView.adapter = joueursAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        btnAjouter.setOnClickListener {
            val nom = editNom.text.toString().trim()
            if (nom.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer un nom", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Check for duplicate name
            val isDuplicate = joueurs.any { it.nom.equals(nom, ignoreCase = true) }
            if (isDuplicate) {
                Toast.makeText(this, "Le nom existe déjà !", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val newJoueur = Joueur(nom, 0)
            joueurs.add(newJoueur)
            joueurs.sortByDescending { it.pointage } // Re-sort after addition
            joueursAdapter.notifyDataSetChanged()
            savePlayersToFile(this, joueurs) // Save to file
            editNom.text.clear()
        }
    }

    private fun openDetailActivity(joueur: Joueur) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("joueur", joueur)
        startActivityForResult(intent, DETAIL_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == DETAIL_REQUEST_CODE && resultCode == RESULT_OK) {
            val updatedJoueur: Joueur? = data?.getParcelableExtra("updated_joueur")
            if (updatedJoueur != null) {
                // Update the player's data in the list
                val index = joueurs.indexOfFirst { it.nom == updatedJoueur.nom }
                if (index != -1) {
                    joueurs[index] = updatedJoueur
                    joueurs.sortByDescending { it.pointage } // Sort again after update
                    joueursAdapter.notifyDataSetChanged() // Notify the adapter
                    savePlayersToFile(this, joueurs) // Save the updated list
                }
            }
        }
    }

}
