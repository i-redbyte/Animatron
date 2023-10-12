package org.redbyte.animatron.tictactoe

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import org.redbyte.animatron.R
import org.redbyte.animatron.base.extensions.onClick

class TicTacToeActivity : AppCompatActivity() {
    private lateinit var tictac: GameBoard
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)
        tictac = findViewById(R.id.gameBoard)
        val btnNewGame = findViewById<Button>(R.id.btnNewGame)
        btnNewGame.onClick {
            tictac.resetGame()
            tictac.invalidate()
            tictac.isPlayGame = true
            btnNewGame.visibility = View.GONE
        }

        tictac.action = { action ->
            if (action.isWinner) showWinner(action)
            if (action.isFinish) {
                tictac.isPlayGame = false
                btnNewGame.visibility = View.VISIBLE
            }
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