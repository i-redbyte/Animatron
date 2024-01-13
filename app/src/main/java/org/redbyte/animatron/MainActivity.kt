package org.redbyte.animatron

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.redbyte.animatron.card.GoCardActivity
import org.redbyte.animatron.compost.CompostActivity
import org.redbyte.animatron.koch.KochSnowActivity
import org.redbyte.animatron.life.LifeGameActivity
import org.redbyte.animatron.newyear.NewYearActivity
import org.redbyte.animatron.pascal.PascalActivity
import org.redbyte.animatron.power.GolangPowerActivity
import org.redbyte.animatron.pythagoras.PythagorasActivity
import org.redbyte.animatron.gophers.GophersActivity
import org.redbyte.animatron.sierpinski.SierpinskiCurveActivity
import org.redbyte.animatron.sierpinski.SierpinskiTriangleActivity
import org.redbyte.animatron.table.TableActivity
import org.redbyte.animatron.tictactoe.TicTacToeActivity
import org.redbyte.animatron.trigan.TriganActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnGoPower).setOnClickListener {
            startActivity(Intent(this, GolangPowerActivity::class.java))
        }
        findViewById<Button>(R.id.btnGophers).setOnClickListener {
            startActivity(GophersActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnTrigan).setOnClickListener {
            startActivity(TriganActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnGoCard).setOnClickListener {
            startActivity(GoCardActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnCompost).setOnClickListener {
            startActivity(CompostActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnLifeGame).setOnClickListener {
            startActivity(LifeGameActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnTicTacToe).setOnClickListener {
            startActivity(TicTacToeActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnSierpinskiCurve).setOnClickListener {
            startActivity(SierpinskiCurveActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnPascal).setOnClickListener {
            startActivity(PascalActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnPythagoras).setOnClickListener {
            startActivity(PythagorasActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnKochSnow).setOnClickListener {
            startActivity(KochSnowActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnSierpinskiTriangle).setOnClickListener {
            startActivity(SierpinskiTriangleActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnRedTable).setOnClickListener {
            startActivity(TableActivity.newInstance(this))
        }
        findViewById<Button>(R.id.btnNewYear).setOnClickListener {
            startActivity(NewYearActivity.newInstance(this))
        }
    }
}
