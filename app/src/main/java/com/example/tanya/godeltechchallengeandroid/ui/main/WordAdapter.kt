package com.example.tanya.godeltechchallengeandroid.ui.main

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tanya.godeltechchallengeandroid.R
import com.example.tanya.godeltechchallengeandroid.domain.entity.Word
import kotlinx.android.synthetic.main.item_word_count.view.*
import javax.inject.Inject

class WordAdapter @Inject constructor() : PagedListAdapter<Word, WordAdapter.WordViewHolder>(DIFF_CALLBACK) {
    private var items: ArrayList<Word> = ArrayList()
    private var progressEnabled = false

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return items[position].hashCode().toLong()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_word_count, viewGroup, false)
        return WordViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (itemCount == 0) return
        holder.bind(items[position])
    }

    fun setProgressEnabled(enabled: Boolean) {
        progressEnabled = enabled
    }

    fun setItems(items: List<Word>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Word) {
            itemView.txt_word.text = item.word
            itemView.txt_count.text = item.count.toString()
            itemView.progress_bar.visibility = if (progressEnabled) View.VISIBLE else View.INVISIBLE
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Word>() {
            override fun areItemsTheSame(oldConcert: Word, newConcert: Word): Boolean {
                return oldConcert.word == newConcert.word
            }

            override fun areContentsTheSame(oldConcert: Word, newConcert: Word): Boolean {
                return oldConcert == newConcert
            }
        }
    }
}