package com.satyamthakur.quotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.satyamthakur.quotify.networking.RetrofitInstance
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val response = try {
                RetrofitInstance.api.getQuotes(10, 1)
            } catch (e: Exception) {
                Log.d("quoteslog", e.toString())
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                Log.d("quoteslog", response.body()!![0].content)
            }
            else {
                Log.d("quotelog", "Request not successful")
            }
        }

    }
}