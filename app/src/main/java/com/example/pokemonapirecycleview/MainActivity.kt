package com.example.pokemonapirecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    var imgUrl=""
    var ids = 1
    var name=""
    var type=""
    private lateinit var pokeList:MutableList<String>
    private lateinit var pokeListName:MutableList<String>
    private lateinit var pokeListType:MutableList<String>
    private lateinit var rvPets: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPets=findViewById(R.id.pokemon_list)

        pokeList= mutableListOf()
        pokeListName= mutableListOf()
        pokeListType= mutableListOf()

        getPokemonImageURL()
    }

    private fun getPokemonImageURL() {
        val client = AsyncHttpClient()

        for (i in 0 until 20) {

            client["https://pokeapi.co/api/v2/pokemon/${ids}", object : JsonHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JsonHttpResponseHandler.JSON
                ) {
                    Log.d("Dog Success", "$json")

                    imgUrl = json.jsonObject.getJSONObject("sprites").getString("back_default")
                    name=json.jsonObject.getJSONArray("forms").getJSONObject(0).getString("name")
                    type=json.jsonObject.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name")

                    pokeList.add(imgUrl)
                    pokeListName.add(name)
                    pokeListType.add(type)



                    val adapter = PokeAdapter(pokeList,pokeListName,pokeListType)
                    rvPets.adapter = adapter
                    rvPets.layoutManager = LinearLayoutManager(this@MainActivity)
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    throwable: Throwable?
                ) {
                    Log.d("Dog Error", errorResponse)
                }
            }]
            ids++
        }
    }
}