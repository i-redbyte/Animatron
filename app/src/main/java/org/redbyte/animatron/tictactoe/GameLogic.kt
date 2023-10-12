package org.redbyte.animatron.tictactoe

object GameLogic {

    fun isGameFinished(board: Board): Boolean {
        return isWin(board, 1) || isWin(board, 2) || isDraw(board)
    }

    fun getWinningLine(board: Board): List<Pair<Int, Int>> {
        // Check rows
        for (i in 0 until 3) {
            if (board[i][0].player != 0
                && board[i][0].player == board[i][1].player
                && board[i][1].player == board[i][2].player
            ) {
                return listOf(Pair(i, 0), Pair(i, 1), Pair(i, 2))
            }
        }

        // Check columns
        for (j in 0 until 3) {
            if (board[0][j].player != 0
                && board[0][j].player == board[1][j].player
                && board[1][j].player == board[2][j].player
            ) {
                return listOf(Pair(0, j), Pair(1, j), Pair(2, j))
            }
        }

        // Check diagonals
        if (board[0][0].player != 0
            && board[0][0].player == board[1][1].player
            && board[1][1].player == board[2][2].player
        ) {
            return listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2))
        }
        if (board[0][2].player != 0
            && board[0][2].player == board[1][1].player
            && board[1][1].player == board[2][0].player
        ) {
            return listOf(Pair(0, 2), Pair(1, 1), Pair(2, 0))
        }

        return emptyList()
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
