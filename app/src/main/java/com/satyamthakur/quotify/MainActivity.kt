package com.satyamthakur.quotify

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.satyamthakur.quotify.databinding.ActivityMainBinding
import com.satyamthakur.quotify.models.QuotesResponseItem
import com.satyamthakur.quotify.networking.RetrofitInstance
import com.satyamthakur.quotify.ui.QuotesPagerAdapter
import com.satyamthakur.quotify.ui.VerticalStackTransformer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var binder: ActivityMainBinding
    private lateinit var qAdapter: QuotesPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binder = binding!!
        setContentView(binder.root)

        // making status bar transparent
        this@MainActivity.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        val mylist = mutableListOf<QuotesResponseItem>()

        qAdapter = QuotesPagerAdapter(this@MainActivity, mylist)
        binder.shimmerContainer.startShimmer()

        binder.viewPager.apply {
            adapter = qAdapter
            offscreenPageLimit = 3
            setPageTransformer(VerticalStackTransformer(0))
        }

        lifecycleScope.launch {
            val response = try {
                RetrofitInstance.api.getQuotes(50, 1)
            } catch (e: Exception) {
                Log.d("quoteslog", e.toString())
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                Log.d("quoteslog", response.body()!![0].content)
                delay(500)
                mylist.addAll(response.body()!!)
                qAdapter.notifyDataSetChanged()
                binder.shimmerContainer.stopShimmer()
                binder.shimmerContainer.isVisible = false
            }
            else {
                Log.d("quotelog", "Request not successful")
                return@launch
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}