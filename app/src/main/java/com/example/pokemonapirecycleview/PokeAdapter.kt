package com.example.pokemonapirecycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokeAdapter(private val pokeList: List<String>,private val pokeName: List<String>, private val pokeListType: List<String>) : RecyclerView.Adapter<PokeAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var names:TextView
        val pokeImage: ImageView
        var types:TextView


        init {
            // Find our RecyclerView item's ImageView for future use
            pokeImage = view.findViewById(R.id.poke_image)
            names=view.findViewById(R.id.name)
            types=view.findViewById(R.id.type)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.poke_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.names.setText("Name: "+pokeName[position])
        holder.types.setText("Type: "+pokeListType[position])
        Glide.with(holder.itemView)
            .load(pokeList[position])
            .centerCrop()
            .into(holder.pokeImage)



    }


    override fun getItemCount() = pokeList.size

}