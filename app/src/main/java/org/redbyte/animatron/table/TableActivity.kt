package org.redbyte.animatron.table

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.redbyte.animatron.R

class TableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)
        val table = findViewById<RedTableView>(R.id.table)
        val matrix = listOf(
            listOf("A", "B", "C"),
            listOf("Ленин", "Сталин", "Че"),
            listOf("Партия", "ум честь и ", "совесть"),
            listOf("С++", "С", "1С")
        )
        table.setColumnHeaders(listOf("1", "2", "3"))
        table.setTableData(matrix)
    }

    companion object {
        fun newInstance(context: Context): Intent = Intent(context, TableActivity::class.java)
    }
}