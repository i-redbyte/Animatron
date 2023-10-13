package org.redbyte.animatron.pascal

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.redbyte.animatron.R

class PascalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pascal)
    }

    companion object {
        fun newInstance(context: Context): Intent = Intent(context, PascalActivity::class.java)
    }
}