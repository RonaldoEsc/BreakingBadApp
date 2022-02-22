package com.hrec.miappdebreakingbad.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.hrec.miappdebreakingbad.MainActivity
import com.hrec.miappdebreakingbad.R
import com.hrec.miappdebreakingbad.databinding.FragmentCharactersBinding
import com.hrec.miappdebreakingbad.interfaces.GoToDescription
import com.hrec.miappdebreakingbad.models.Characters
import com.squareup.picasso.Picasso

class MyItemRecyclerViewAdapter(
    private val values: List<Characters>, private val mContext: MainActivity
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val title = "${item.name} (${item.nickname})\n"
        holder.idView.text = title
        //holder.contentView.text =
        Picasso.get().load(item.img).placeholder(R.drawable.breaking_bad_logo).into(holder.imgContent)
        holder.card.setOnClickListener {
            val inter: GoToDescription = mContext
            inter.onDescription(item)
        }

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentCharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val card: CardView = binding.cardView
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val imgContent: ImageView = binding.imgCharacter

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}