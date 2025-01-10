package com.example.listedespointages

import Joueur
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

fun savePlayersToFile(context: Context, players: ArrayList<Joueur>) {
    try {
        val gson = Gson()
        val jsonString = gson.toJson(players)
        val file = File(context.filesDir, "joueurs.json")
        file.writeText(jsonString)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun loadPlayersFromFile(context: Context): ArrayList<Joueur> {
    return try {
        val file = File(context.filesDir, "joueurs.json")
        if (!file.exists()) return ArrayList()

        val jsonString = file.readText()
        val type = object : TypeToken<ArrayList<Joueur>>() {}.type
        Gson().fromJson(jsonString, type)
    } catch (e: Exception) {
        e.printStackTrace()
        ArrayList()
    }
}
