package org.redbyte.animatron.power

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.activity_golang_power.*
import org.redbyte.animatron.R
import org.redbyte.animatron.power.animatron.PlainAnimationListener
import org.redbyte.animatron.power.animatron.PlainAnimatorListener
import org.redbyte.animatron.power.animatron.PlainTransitionListener

class GolangPowerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_golang_power)
        setupViews()
    }

    private fun setupViews() {
        setupRecyclerView()
        btnNext.setOnClickListener {
            animateToSecondScene()
        }
    }

    private fun animateToSecondScene() {
        gopherRootMotion.setTransitionListener(object : PlainTransitionListener() {
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                val duration = 600L

                ivSceneCircleMask.alpha = 1f
                animateViewAlpha(ivSceneCircleMask, 10, 0)
                val mobileImageY = ivGopher.y
                moveViewByY(ivGopher, duration, 100, -(mobileImageY - DELTA_Y))
            }
        })
        //Start animation semicircle
        gopherRootMotion.setTransition(R.id.start, R.id.end)
        gopherRootMotion.transitionToEnd()
    }

    private fun setupRecyclerView() {
        // TODO: Red_byte 2019-12-22 release it 
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
                override fun onAnimationStart(animation: Animator?, isReverse: Boolean) {
                    onAnimationStarted.invoke()
                }

                override fun onAnimationEnd(p0: Animator?) {
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

    companion object {
        private const val DELTA_Y = 580
    }
}

