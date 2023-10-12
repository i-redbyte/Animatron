package org.redbyte.animatron.tictactoe

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.redbyte.animatron.R
import org.redbyte.animatron.compost.CompostActivity

class TicTacToeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)
    }

    companion object {
        fun newInstance(context: Context): Intent = Intent(context, TicTacToeActivity::class.java)
    }
}