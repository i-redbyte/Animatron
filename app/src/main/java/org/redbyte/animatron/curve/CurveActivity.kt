package org.redbyte.animatron.curve

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.redbyte.animatron.R

class CurveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curve)
    }

    companion object {
        fun newInstance(context: Context): Intent = Intent(context, CurveActivity::class.java)
    }
}