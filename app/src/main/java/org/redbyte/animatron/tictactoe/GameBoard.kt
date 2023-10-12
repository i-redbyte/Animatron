package org.redbyte.animatron.tictactoe

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import org.redbyte.animatron.tictactoe.ActionGame.Cross
import org.redbyte.animatron.tictactoe.ActionGame.Zero

data class GameCell(val x: Int, val y: Int, val player: Int)

typealias Board = List<List<GameCell>>

typealias PlayerAction = (ActionGame) -> Unit

class GameBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 5f
    }

    private var board: Board = List(3) { i -> List(3) { j -> GameCell(i, j, 0) } }
    private var currentPlayer = 1
    var action: PlayerAction = {}
    var isPlayGame = true
    override fun onTouchEvent(event: MotionEvent): Boolean {
        //if (!isPlayGame) return false
        if (event.action == MotionEvent.ACTION_DOWN) {
            val cellWidth = width / 3
            val cellHeight = height / 3
            val i = (event.y / cellHeight).toInt()
            val j = (event.x / cellWidth).toInt()

            if (isCellEmpty(i, j)) {
                board = updateBoard(i, j)
                currentPlayer = 3 - currentPlayer
                val isWinner = GameLogic.isGameFinished(board)
                        && GameLogic.getWinningLine(board).isNotEmpty()
                val isFinish = GameLogic.isGameFinished(board)
                val player = if (currentPlayer == 2) Cross(isWinner, isFinish)
                else Zero(isWinner, isFinish)
                action(player)
                invalidate()
            }
        }
        return isPlayGame
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width.toFloat()
        val height = height.toFloat()
        val cellWidth = width / 3
        val cellHeight = height / 3

        drawBoard(canvas, cellWidth, cellHeight)

        board.flatten().forEach { cell ->
            when (cell.player) {
                1 -> drawX(canvas, cell, cellWidth, cellHeight)
                2 -> drawO(canvas, cell, cellWidth, cellHeight)
            }
        }

        if (GameLogic.getWinningLine(board).isNotEmpty()) {
            val winningLine = GameLogic.getWinningLine(board)
            drawWinningLine(canvas, winningLine, cellWidth, cellHeight)
        }
    }

    private fun drawWinningLine(
        canvas: Canvas,
        line: List<Pair<Int, Int>>,
        cellWidth: Float,
        cellHeight: Float
    ) {
        val paint = Paint().apply {
            color = Color.GREEN
            strokeWidth = 14f
        }

        val startX = line.first().second * cellWidth + cellWidth / 2
        val startY = line.first().first * cellHeight + cellHeight / 2
        val endX = line.last().second * cellWidth + cellWidth / 2
        val endY = line.last().first * cellHeight + cellHeight / 2

        canvas.drawLine(startX, startY, endX, endY, paint)
    }

    private fun drawBoard(canvas: Canvas, cellWidth: Float, cellHeight: Float) {
        (1 until 3).forEach {
            // Horizontal lines
            canvas.drawLine(
                0f,
                it * cellHeight,
                width.toFloat(),
                it * cellHeight,
                paint
            )
            // Vertical lines
            canvas.drawLine(
                it * cellWidth,
                0f,
                it * cellWidth,
                height.toFloat(),
                paint
            )
        }
    }

    private fun drawX(canvas: Canvas, cell: GameCell, cellWidth: Float, cellHeight: Float) {
        val startX = cell.y * cellWidth
        val startY = cell.x * cellHeight
        val endX = (cell.y + 1) * cellWidth
        val endY = (cell.x + 1) * cellHeight

        canvas.drawLine(startX, startY, endX, endY, paint)
        canvas.drawLine(endX, startY, startX, endY, paint)
    }

    private fun drawO(canvas: Canvas, cell: GameCell, cellWidth: Float, cellHeight: Float) {
        val centerX = (cell.y + 0.5f) * cellWidth
        val centerY = (cell.x + 0.5f) * cellHeight
        val radius = minOf(cellWidth, cellHeight) / 3

        canvas.drawCircle(centerX, centerY, radius, paint)
    }

    private fun isCellEmpty(x: Int, y: Int): Boolean {
        return board[x][y].player == 0
    }

    private fun updateBoard(x: Int, y: Int): Board {
        return board.mapIndexed { i, row ->
            row.mapIndexed { j, cell ->
                if (i == x && j == y && cell.player == 0)
                    GameCell(i, j, currentPlayer)
                else
                    cell
            }
        }
    }

    fun resetGame() {
        board = List(3) { i -> List(3) { j -> GameCell(i, j, 0) } }
        currentPlayer = 1
    }
}

sealed class ActionGame(val isWinner: Boolean, val isFinish: Boolean) {
    class Cross(isWinner: Boolean, isFinish: Boolean) : ActionGame(isWinner, isFinish)
    class Zero(isWinner: Boolean, isFinish: Boolean) : ActionGame(isWinner, isFinish)
}