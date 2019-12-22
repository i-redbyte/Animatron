package org.redbyte.animatron

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.redbyte.animatron.threescenes.ThreeScenesActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnThreeScenes.setOnClickListener {
            startActivity(Intent(this, ThreeScenesActivity::class.java))
        }
    }
}
