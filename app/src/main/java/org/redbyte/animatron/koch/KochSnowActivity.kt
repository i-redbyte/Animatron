package org.redbyte.animatron.koch

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.redbyte.animatron.R

class KochSnowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koch_snow)
    }

    companion object {
        fun newInstance(context: Context): Intent = Intent(context, KochSnowActivity::class.java)
    }
}