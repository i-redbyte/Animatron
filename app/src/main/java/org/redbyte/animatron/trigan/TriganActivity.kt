package org.redbyte.animatron.trigan

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.redbyte.animatron.R
import org.redbyte.animatron.base.extensions.getScreenWidth
import kotlin.math.E
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class TriganActivity : AppCompatActivity() {
    private val ivCosmoGopher by lazy { findViewById<ImageView>(R.id.ivCosmoGopher) }
    private val sky by lazy { findViewById<SkyView>(R.id.sky) }
    private var withCosmoDiscoStyle = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trigan)
        Glide.with(this)
            .load(R.drawable.ic_go_cosmos)
            .into(ivCosmoGopher)
        ivCosmoGopher.setOnClickListener {
            withCosmoDiscoStyle = !withCosmoDiscoStyle
            val msg = when (withCosmoDiscoStyle) {
                true -> "COSMO DISCO STYLE ON"
                false -> "COSMO DISCO STYLE OFF"
            }
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        setupAnimation()
    }

    private fun setupAnimation() {
        val maxValue = getScreenWidth() - (ivCosmoGopher.width + ivCosmoGopher.height) * 2
        val minValue = -(maxValue / 4)

        ValueAnimator.ofFloat(0f, 5.5f).apply {
            var incrementalValue = 0f
            var usingCos = true
            duration = COSMO_DURATION
            val d = PI * 4
            addUpdateListener { animation ->
                val value = (animation.animatedValue as Float)
                ivCosmoGopher.translationX = incrementalValue
                ivCosmoGopher.translationY = if (usingCos) {
                    cos(value * d).toFloat() * ivCosmoGopher.width
                } else {
                    sin(value * d).toFloat() * ivCosmoGopher.width
                }
                if (incrementalValue.toInt() % 4 == 0 && withCosmoDiscoStyle) sky.invalidate()
                incrementalValue += INC_VALUE
                if (incrementalValue > maxValue) {
                    sky.invalidate()
                    usingCos = false
                    incrementalValue = minValue.toFloat()
                    ivCosmoGopher.translationX = minValue.toFloat()
                } else if (incrementalValue <= minValue) {
                    usingCos = true
                }
            }
            setTarget(ivCosmoGopher)
            start()
            repeatCount = Animation.INFINITE
        }
    }

    companion object {
        const val COSMO_DURATION = 32000L
        const val INC_VALUE = E.toFloat()
        fun newInstance(context: Context): Intent = Intent(context, TriganActivity::class.java)
    }
}
