package com.satyamthakur.quotify.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.satyamthakur.quotify.R
import com.satyamthakur.quotify.databinding.QuoteItemBinding
import com.satyamthakur.quotify.models.QuoteResponseItem
import com.satyamthakur.quotify.models.QuotesResponseItem

class QuotesPagerAdapter(
    var context: Context,
    var quotesList: List<QuoteResponseItem>
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

        binding.quoteCslParent.setOnLongClickListener {

            val textToShare = "" + quotesList[position].q + "\n\n~" + quotesList[position].a

            // Share quotes to other apps
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare)
            context.startActivity(Intent.createChooser(shareIntent, "Share via"))

            true
        }


        binding.tvContent.text = quotesList[position].q
        binding.tvAuthor.text = quotesList[position].a
        

        if (position == 0) {
            binding.quoteCslParent.setBackgroundColor(Color.parseColor("#1976D2"))
        }
        else if (position == quotesList.size - 2 || position == quotesList.size - 12)
            binding.quoteCslParent.setBackgroundColor(Color.parseColor("#1976D2"))
        else
            binding.quoteCslParent.setBackgroundColor(Color.parseColor(colorList.random()))
    }

    companion object ColorsBack {
        val colorList = listOf<String>(
            "#16A085",
            "#27AE60",
            "#2980B9",
            "#8E44AD",
            "#F39C12",
            "#D35400",
            "#C0392B"
        )
    }

}