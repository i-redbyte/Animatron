package org.redbyte.animatron.life

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.redbyte.animatron.R

class LifeGameActivity : AppCompatActivity() {
    private val world by lazy { findViewById<WorldLifeView>(R.id.worldLife) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_game)
        world.generateWorld(0xF)
    }

    companion object {
        fun newInstance(context: Context): Intent = Intent(context, LifeGameActivity::class.java)
    }
}