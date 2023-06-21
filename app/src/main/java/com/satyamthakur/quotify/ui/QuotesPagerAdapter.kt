package com.satyamthakur.quotify.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.satyamthakur.quotify.R
import com.satyamthakur.quotify.databinding.QuoteItemBinding
import com.satyamthakur.quotify.models.QuotesResponseItem


class QuotesPagerAdapter(
    var context: Context,
    var quotesList: List<QuotesResponseItem>
): RecyclerView.Adapter<QuotesPagerAdapter.QuoteViewHolder>() {

    inner class QuoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return QuoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return quotesList.size
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val binding = QuoteItemBinding.bind(holder.itemView)

//        val clipboardManager = holder.itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

//        binding.itemParentCsl.setOnLongClickListener {
//            val clipData = ClipData.newPlainText("text", imageModelList[position].quote)
//            clipboardManager.setPrimaryClip(clipData)
//            Toast.makeText(holder.itemView.context, "Text copied to clipboard", Toast.LENGTH_LONG).show()
//
//            true
//        }

        binding.tvContent.text = quotesList[position].content
    }

}