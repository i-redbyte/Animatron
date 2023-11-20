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
            listOf("Java", "Kotlin", "Groovy"),
            listOf("Haskell", "Lisp", "OCaml"),
            listOf("Pascal", "Basic", "Fortran"),
            listOf("С++", "С", "Rust")
        )
        table.setColumnHeaders(listOf("Column1", "Column2", "Column3"))
        table.setTableData(matrix)
    }

    companion object {
        fun newInstance(context: Context): Intent = Intent(context, TableActivity::class.java)
    }
}