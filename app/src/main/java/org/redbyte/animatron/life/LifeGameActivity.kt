package org.redbyte.animatron.life

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.redbyte.animatron.R
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds


class LifeGameActivity : AppCompatActivity() {
    private val world by lazy { findViewById<WorldLifeView>(R.id.worldLife) }
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_game)
        setupViews()
        startGame()
    }

    private fun startGame() {
        delayFlow(175.milliseconds) { getNextStatus() }.onEach {
            world.setChangedList(it)
            world.invalidate()
        }.launchIn(lifecycleScope)
    }

    private fun delayFlow(
        delayTime: Duration, initialDelay: Duration = Duration.ZERO, nextStep: () -> MatrixWorld
    ) = flow {
        delay(initialDelay)
        while (true) {
            emit(nextStep())
            delay(delayTime)
        }
    }

    private fun setupViews() {
        world.generateWorld(1680)
        toolbar.title = getString(R.string.life_game)
        toolbar.setNavigationOnClickListener { finish() }
    }

    private val ref = arrayOf(
        intArrayOf(-1, -1),
        intArrayOf(-1, 0),
        intArrayOf(-1, 1),
        intArrayOf(0, -1),
        intArrayOf(0, 1),
        intArrayOf(1, -1),
        intArrayOf(1, 0),
        intArrayOf(1, 1)
    )

    fun getNextStatus(): MatrixWorld {
        with(world) {
            val newPixelArray = Array(matrix.size) { IntArray(matrix[0].size) }
            for (i in newPixelArray.indices) {
                for (j in newPixelArray[0].indices) {
                    val newValue = if (isLive(matrix, i, j)) 1 else 0
                    if (newPixelArray[i][j] == newValue) continue
                    newPixelArray[i][j] = newValue
                }
            }

            for (i in matrix.indices) {
                for (j in matrix[0].indices) {
                    matrix[i][j] = newPixelArray[i][j]
                }
            }
            return newPixelArray
        }
    }

    private fun isLive(pixelArray: Array<IntArray>, i: Int, j: Int): Boolean {
        var liveNeighborsCount = 0
        for (k in ref.indices) {
            if (i + ref[k][0] < 0 || j + ref[k][1] < 0 || i + ref[k][0] >= pixelArray.size || j + ref[k][1] >= pixelArray[0].size) continue
            liveNeighborsCount += pixelArray[i + ref[k][0]][j + ref[k][1]]
        }

        return if (pixelArray[i][j] == 0) {
            liveNeighborsCount == 3
        } else {
            liveNeighborsCount in 2..3
        }
    }

    companion object {
        fun newInstance(context: Context): Intent = Intent(context, LifeGameActivity::class.java)
    }
}