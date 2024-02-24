package org.redbyte.animatron.power

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import org.redbyte.animatron.R
import org.redbyte.animatron.base.extensions.getScreenHeight
import org.redbyte.animatron.power.animatron.PlainAnimationListener
import org.redbyte.animatron.power.animatron.PlainAnimatorListener
import org.redbyte.animatron.power.animatron.PlainTransitionListener

class GolangPowerActivity : AppCompatActivity() {
    private val btnNext by lazy { findViewById<Button>(R.id.btnNext) }

    private val gopherRootMotion by lazy { findViewById<MotionLayout>(R.id.gopherRootMotion) }
    private val ivSceneCircleMask by lazy { findViewById<ImageView>(R.id.ivSceneCircleMask) }
    private val ivGopher by lazy { findViewById<ImageView>(R.id.ivGopher) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_golang_power)
        setupViews()
    }

    private fun setupViews() {
        btnNext.setOnClickListener {
            animateScene()
        }
    }

    private fun animateScene() {
        val deltaY = getScreenHeight() / 2.5f
        gopherRootMotion.setTransitionListener(object : PlainTransitionListener() {
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                ivSceneCircleMask.alpha = 1f
                animateViewAlpha(ivSceneCircleMask, 10, 0)
                val duration = 600L
                val mobileImageY = ivGopher.y
                moveViewByY(ivGopher, duration, 100, -(mobileImageY - deltaY))
            }
        })
        gopherRootMotion.setTransition(R.id.start, R.id.end)
        gopherRootMotion.transitionToEnd()
    }

    private fun moveViewByY(
        view: View,
        duration: Long,
        delay: Long,
        value: Float,
        onAnimationStarted: () -> Unit = {},
        onAnimationCompleted: () -> Unit = {}
    ) {
        ObjectAnimator.ofFloat(view, "translationY", value).apply {
            this.duration = duration
            startDelay = delay

            addListener(object : PlainAnimatorListener() {
                override fun onAnimationStart(animation: Animator) {
                    onAnimationStarted.invoke()
                }

                override fun onAnimationEnd(animation: Animator) {
                    onAnimationCompleted.invoke()
                }
            })

            start()
        }
    }

    private fun animateViewAlpha(
        view: View,
        duration: Long,
        delay: Long = 0,
        onAnimationStarted: () -> Unit = {},
        onAnimationCompleted: () -> Unit = {}
    ) {
        val alphaAnimation = AlphaAnimation(0.0f, 1.0f).apply {
            this.duration = duration
            startOffset = delay
            fillAfter = true

            setAnimationListener(object : PlainAnimationListener() {
                override fun onAnimationStart(animation: Animation?) {
                    onAnimationStarted()
                }

                override fun onAnimationEnd(animation: Animation?) {
                    onAnimationCompleted()
                }
            })
        }

        view.startAnimation(alphaAnimation)
    }
}

