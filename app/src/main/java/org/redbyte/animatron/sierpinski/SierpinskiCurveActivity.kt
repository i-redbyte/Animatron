package org.redbyte.animatron.sierpinski

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.redbyte.animatron.R
import org.redbyte.animatron.base.extensions.hideKeyboard
import java.lang.Exception

class SierpinskiCurveActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sierpinski_curve)
        val levelView = findViewById<EditText>(R.id.etCurveLevel)
        val scv = findViewById<SierpinskiCurveView>(R.id.scv)
        hideDigits89(levelView)
        scv.setColor(getColor(R.color.colorRed))
        findViewById<Button>(R.id.btnOk).setOnClickListener {
            hideKeyboard()
            try {
                val level = levelView.text.toString().toInt()
                scv.setCurveLevel(level = level)
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    getString(R.string.error_curve_level),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun hideDigits89(editText: EditText) {
        val inputFilter = InputFilter { source, start, end, dest, dstart, dend ->

            for (i in start until end) {
                if (Character.isDigit(source[i]) && (source[i] == '8' || source[i] == '9')) {
                    return@InputFilter ""
                }
            }
            null
        }

        editText.filters = arrayOf(inputFilter, InputFilter.LengthFilter(1))
    }

    companion object {
        fun newInstance(context: Context): Intent =
            Intent(context, SierpinskiCurveActivity::class.java)
    }
}