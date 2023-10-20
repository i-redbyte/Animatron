package org.redbyte.animatron.sierpinski

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.redbyte.animatron.R

class SierpinskiTriangleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sierpinski_triangle)
    }

    companion object {
        fun newInstance(context: Context): Intent =
            Intent(context, SierpinskiTriangleActivity::class.java)
    }
}