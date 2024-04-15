package org.redbyte.animatron.curve

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.redbyte.animatron.R

class CurveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curve)
        val ellipticCurveView = findViewById<EllipticCurveView>(R.id.rndCurveView)
        ellipticCurveView.setCurveParameters(
            a = -1.0,
            b = 2.0,
            xMin = -5.0,
            xMax = 5.0,
            step = 0.0001
        )
    }

    companion object {
        fun newInstance(context: Context): Intent = Intent(context, CurveActivity::class.java)
    }
}