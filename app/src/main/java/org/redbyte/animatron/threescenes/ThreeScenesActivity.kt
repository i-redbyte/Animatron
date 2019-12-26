package org.redbyte.animatron.threescenes

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.activity_three_scenes.*
import org.redbyte.animatron.R

class ThreeScenesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three_scenes)
        setupViews()
    }

    private fun setupViews() {
        setupRecyclerView()
        btnNext.setOnClickListener {
            animateToSecondScene()
        }
    }

    private fun animateToSecondScene() {
        activity_gopher_root_motion.setTransitionListener(object : PlainTransitionListener() {
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                ivSceneCircleMask.alpha = 1f
                animateViewAlpha(ivSceneCircleMask, 10, 0)
            }
        })
        //Start animation semicircle
        activity_gopher_root_motion.setTransition(R.id.start, R.id.end)
        activity_gopher_root_motion.transitionToEnd()
    }

    private fun setupRecyclerView() {
        // TODO: Red_byte 2019-12-22 release it 
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
                onAnimationStarted.invoke()
            }

            override fun onAnimationEnd(animation: Animation?) {
                onAnimationCompleted.invoke()
            }
        })
    }

    view.startAnimation(alphaAnimation)
}