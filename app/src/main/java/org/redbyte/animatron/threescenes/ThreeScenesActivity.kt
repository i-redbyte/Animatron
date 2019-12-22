package org.redbyte.animatron.threescenes

import android.os.Bundle
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
                // TODO: Red_byte 2019-12-22 refresh view
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
