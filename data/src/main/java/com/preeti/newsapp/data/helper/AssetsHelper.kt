package com.preeti.newsapp.data.helper

import android.content.Context
import com.google.gson.Gson
import com.preeti.newsapp.domain.model.Languages
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

import javax.inject.Singleton

@Singleton
class AssetsHelper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) {

    fun getLanguages(): Languages {
        val file = loadFile("languages.json")
        return gson.fromJson(file, Languages::class.java)
    }

    private fun loadFile(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }
    }
}
