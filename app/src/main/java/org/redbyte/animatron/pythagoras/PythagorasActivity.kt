package org.redbyte.animatron.pythagoras

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.redbyte.animatron.R
import org.redbyte.animatron.tictactoe.TicTacToeActivity

class PythagorasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pythagoras)
    }
    companion object {
        fun newInstance(context: Context): Intent = Intent(context, PythagorasActivity::class.java)
    }
}