package com.satyamthakur.quotify.ui

import android.content.res.Resources
import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2

class VerticalStackTransformer(private val offscreenPageLimit: Int) : ViewPager2.PageTransformer {

    companion object {

        private const val DEFAULT_TRANSLATION_Y = .0f

        private const val SCALE_FACTOR = .1f
        private const val DEFAULT_SCALE = 1f

        private const val ALPHA_FACTOR = .5f
        private const val DEFAULT_ALPHA = 1f

    }

    override fun transformPage(page: View, position: Float) {
        page.apply {
            ViewCompat.setElevation(page, -Math.abs(position))

            val scaleFactor = -SCALE_FACTOR * position + DEFAULT_SCALE
            val alphaFactor = -ALPHA_FACTOR * position + DEFAULT_ALPHA

            when {
                position <= 0f -> {
                    translationY = DEFAULT_TRANSLATION_Y
                    scaleX = DEFAULT_SCALE
                    scaleY = DEFAULT_SCALE
                    alpha = DEFAULT_ALPHA + position
                }
                position <= offscreenPageLimit - 1 -> {
                    scaleX = scaleFactor
                    scaleY = DEFAULT_SCALE
                    translationY = (-height * position + 0.dpToPx * position)
                    alpha = alphaFactor
                }
                else -> {
                    translationY = DEFAULT_TRANSLATION_Y
                    scaleX = DEFAULT_SCALE
                    scaleY = DEFAULT_SCALE
                    alpha = alphaFactor
                }
            }
        }
    }

    val Int.dpToPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()
}
