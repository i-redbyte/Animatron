package org.redbyte.animatron.trigan

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import org.redbyte.animatron.R
import org.redbyte.animatron.base.extensions.getScreenWidth
import kotlin.math.PI
import kotlin.math.sin

class TriganActivity : AppCompatActivity() {
    private val ivCosmoGopher by lazy { findViewById<ImageView>(R.id.ivCosmoGopher) }
    private val sky by lazy { findViewById<SkyView>(R.id.sky) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trigan)
        setupAnimation()
    }

    private fun setupAnimation() {
        val maxValue = getScreenWidth() - (ivCosmoGopher.width + ivCosmoGopher.height) * 2
        ValueAnimator.ofFloat(0f, 5.5f).apply {
            var incrementalValue = 0f
            var k = INC_VALUE
            duration = COSMO_DURATION
            addUpdateListener { animation ->
                val value = (animation.animatedValue as Float)
                ivCosmoGopher.translationX = incrementalValue
                ivCosmoGopher.translationY = sin(value * PI * 2).toFloat() * 300
                incrementalValue += k
                if (incrementalValue.toInt() % 4 == 0) sky.invalidate()
                if (incrementalValue > maxValue) {
                    k = -INC_VALUE
                } else if (incrementalValue <= 0) {
                    k = INC_VALUE
                }
            }
            setTarget(ivCosmoGopher)
            start()
            repeatCount = Animation.INFINITE
        }
    }

    companion object {
        const val COSMO_DURATION = 16500L
        const val INC_VALUE = 2.4f
        fun newInstance(context: Context): Intent = Intent(context, TriganActivity::class.java)
    }
}
