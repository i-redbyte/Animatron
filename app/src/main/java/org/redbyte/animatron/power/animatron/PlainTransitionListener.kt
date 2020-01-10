package org.redbyte.animatron.power.animatron

import androidx.constraintlayout.motion.widget.MotionLayout

open class PlainTransitionListener : MotionLayout.TransitionListener{
    override fun onTransitionChange(motionLayout: MotionLayout, startId: Int, endId: Int, progress: Float) {}

    override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {}

    override fun onTransitionTrigger(motionLayout: MotionLayout, triggerId: Int, positive: Boolean, progress: Float) {}

    override fun onTransitionStarted(motionLayout: MotionLayout, startId: Int, endId: Int) {}

}