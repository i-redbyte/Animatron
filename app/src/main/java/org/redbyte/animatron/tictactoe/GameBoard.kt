package org.redbyte.animatron.tictactoe

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

data class GameCell(val x: Int, val y: Int, val player: Int)

typealias Board = List<List<GameCell>>

class GameBoard(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 5f
    }

    private var board: Board = List(3) { i -> List(3) { j -> GameCell(i, j, 0) } }
    private var currentPlayer = 1

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
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val cellWidth = width / 3
            val cellHeight = height / 3
            val i = (event.y / cellHeight).toInt()
            val j = (event.x / cellWidth).toInt()

            if (isCellEmpty(i, j)) {
                board = updateBoard(i, j)
                currentPlayer = 3 - currentPlayer
                invalidate()
            }

            if (GameLogic.isGameFinished(board)) {
                // Handle game over (win or draw)
                resetGame()
            }
        }
        return true
    }

    private fun drawBoard(canvas: Canvas, cellWidth: Float, cellHeight: Float) {
        (1 until 3).forEach {
            // Horizontal lines
            canvas.drawLine(0f, it * cellHeight, width.toFloat(), it * cellHeight, paint)
            // Vertical lines
            canvas.drawLine(it * cellWidth, 0f, it * cellWidth, height.toFloat(), paint)
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

    private fun resetGame() {
        board = List(3) { i -> List(3) { j -> GameCell(i, j, 0) } }
        currentPlayer = 1
    }
}
