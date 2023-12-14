package org.redbyte.animatron.newyear

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.redbyte.animatron.R

class NewYearActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_year)
    }

    companion object {
        fun newInstance(context: Context): Intent = Intent(context, NewYearActivity::class.java)
    }
}