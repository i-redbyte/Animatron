package org.redbyte.animatron.tictactoe

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.redbyte.animatron.R

class TicTacToeActivity : AppCompatActivity() {
    private lateinit var tictac: GameBoard
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)
        tictac = findViewById(R.id.gameBoard)

        tictac.action = { action ->
            if (action.isWinner) showWinner(action)

        }
    }

    private fun showWinner(action: ActionGame) {
        val playerName = when (action) {
            is ActionGame.Cross -> "Cross"
            is ActionGame.Zero -> "Zero"
        }
        Toast.makeText(this, "$playerName is winn!", Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance(context: Context): Intent = Intent(context, TicTacToeActivity::class.java)
    }
}