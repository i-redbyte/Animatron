package org.redbyte.animatron

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.redbyte.animatron.card.GoCardActivity
import org.redbyte.animatron.compost.CompostActivity
import org.redbyte.animatron.life.LifeGameActivity
import org.redbyte.animatron.power.GolangPowerActivity
import org.redbyte.animatron.scrollman.ScrollmanActivity
import org.redbyte.animatron.trigan.TriganActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnGoPower).setOnClickListener {
            startActivity(Intent(this, GolangPowerActivity::class.java))
        }
        findViewById<Button>(R.id.btnGophers).setOnClickListener {
            startActivity(ScrollmanActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnTrigan).setOnClickListener {
            startActivity(TriganActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnGoCard).setOnClickListener {
            startActivity(GoCardActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnCompost).setOnClickListener {
            startActivity(CompostActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnLifeGame).setOnClickListener {
            startActivity(LifeGameActivity.newInstance(this))
        }
    }
}
