package org.redbyte.animatron

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.redbyte.animatron.card.GoCardActivity
import org.redbyte.animatron.power.GolangPowerActivity
import org.redbyte.animatron.scrollman.ScrollmanActivity
import org.redbyte.animatron.trigan.TriganActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGoPower.setOnClickListener {
            startActivity(Intent(this, GolangPowerActivity::class.java))
        }
        btnGophers.setOnClickListener {
            startActivity(ScrollmanActivity.open(this))
        }
        btnTrigan.setOnClickListener {
            startActivity(TriganActivity.open(this))
        }
        btnGoCard.setOnClickListener {
            startActivity(GoCardActivity.open(this))
        }
    }
}
