package org.redbyte.animatron.tictactoe

object GameLogic {

    fun isGameFinished(board: Board): Boolean {
        return isWin(board, 1) || isWin(board, 2) || isDraw(board)
    }

    private fun isWin(board: Board, player: Int): Boolean {
        val winningPatterns = listOf(
            listOf(Pair(0, 0), Pair(0, 1), Pair(0, 2)),
            listOf(Pair(1, 0), Pair(1, 1), Pair(1, 2)),
            listOf(Pair(2, 0), Pair(2, 1), Pair(2, 2)),
            listOf(Pair(0, 0), Pair(1, 0), Pair(2, 0)),
            listOf(Pair(0, 1), Pair(1, 1), Pair(2, 1)),
            listOf(Pair(0, 2), Pair(1, 2), Pair(2, 2)),
            listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2)),
            listOf(Pair(0, 2), Pair(1, 1), Pair(2, 0))
        )

        return winningPatterns.any { pattern ->
            pattern.all { (x, y) -> board[x][y].player == player }
        }
    }

    private fun isDraw(board: Board): Boolean {
        return board.flatten().none { it.player == 0 }
    }
}
