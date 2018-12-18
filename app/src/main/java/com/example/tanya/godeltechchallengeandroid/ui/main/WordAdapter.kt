package com.example.tanya.godeltechchallengeandroid.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tanya.godeltechchallengeandroid.R
import com.example.tanya.godeltechchallengeandroid.domain.entity.Word
import kotlinx.android.synthetic.main.item_word_count.view.*
import javax.inject.Inject

class WordAdapter @Inject constructor(): RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private var wordSet: ArrayList<Word> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_word_count, viewGroup, false)
        return WordViewHolder(view)
    }

    override fun getItemCount(): Int {
         return wordSet.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, p1: Int) {
        if (itemCount == 0) return
        holder.bind(wordSet[p1])
    }

    fun setItems(map: MutableMap<String, Int>) {
        map.forEach {
            wordSet.add(Word(it.key, it.value))
        }
        notifyDataSetChanged()
    }

    fun addItem(word: Word) {
        wordSet.add(word)
        notifyItemInserted(wordSet.size)
    }

    inner class WordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: Word) {
            itemView.txt_word.text = item.word
            itemView.txt_count.text = item.count.toString()

        }

    }
}