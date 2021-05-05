package org.redbyte.animatron.card

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.go_card_back.*
import kotlinx.android.synthetic.main.go_card_front.*
import org.redbyte.animatron.R
import org.redbyte.animatron.trigan.TriganActivity

class GoCardActivity : AppCompatActivity() {
    private lateinit var mSetRightOut: AnimatorSet
    private lateinit var mSetLeftIn: AnimatorSet
    private var mIsBackVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_card)
        setupAnimation()
        setupViews()
    }

    private fun setupViews() {
        cvGoCardFront.setOnClickListener { flipCard() }
        cvGoCardBack.setOnClickListener { flipCard() }
        scv.setColor(getColor(R.color.colorYellow))
    }

    private fun setupAnimation() {
        mSetRightOut =
            AnimatorInflater.loadAnimator(this, R.animator.flip_out_animation) as AnimatorSet
        mSetLeftIn =
            AnimatorInflater.loadAnimator(this, R.animator.flip_in_animation) as AnimatorSet
        changeCameraDistance()
    }

    private fun changeCameraDistance() {
        val distance = 180000
        val scale = resources.displayMetrics.density * distance
        cvGoCardFront.cameraDistance = scale
        cvGoCardBack.cameraDistance = scale
    }

    private fun flipCard() {
        mIsBackVisible = if (!mIsBackVisible) {
            mSetRightOut.setTarget(cvGoCardFront)
            mSetLeftIn.setTarget(cvGoCardBack)
            mSetRightOut.start()
            mSetLeftIn.start()
            true
        } else {
            mSetRightOut.setTarget(cvGoCardBack)
            mSetLeftIn.setTarget(cvGoCardFront)
            mSetRightOut.start()
            mSetLeftIn.start()
            false
        }
    }

    companion object{
        fun open(context: Context): Intent = Intent(context, GoCardActivity::class.java)
    }
}