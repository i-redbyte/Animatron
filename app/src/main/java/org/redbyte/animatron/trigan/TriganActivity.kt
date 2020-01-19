package org.redbyte.animatron.trigan

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_trigan.*
import org.redbyte.animatron.R
import kotlin.math.PI
import kotlin.math.sin

class TriganActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trigan)
        setupAnimation()
    }

    private fun setupAnimation() {
      ValueAnimator.ofFloat(0f, 5.5f).apply {
            var incrementalValue = 0f
            duration = COSMO_DURATION

            addUpdateListener { animation ->
                val value = (animation.animatedValue as Float)
                ivCosmoGopher.translationX = incrementalValue
                ivCosmoGopher.translationY = sin(value * PI * 2).toFloat() * 300
                incrementalValue += 2f
            }
            setTarget(ivCosmoGopher)
           start()
        }
    }

    companion object {
        const val COSMO_DURATION = 16500L
        fun open(context: Context): Intent = Intent(context, TriganActivity::class.java)
    }
}
