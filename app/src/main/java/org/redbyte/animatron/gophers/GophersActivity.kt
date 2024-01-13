package org.redbyte.animatron.gophers

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.redbyte.animatron.R
import org.redbyte.animatron.base.DelegationAdapter

class GophersActivity : AppCompatActivity() {
    private val rvGophers by lazy { findViewById<RecyclerView>(R.id.rvGophers) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_go)
        setupRecycler()
    }

    @SuppressLint("Recycle")
    private fun setupRecycler() {
        val linearLayoutManager =
            SpeedyLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val gophersDelegate = GophersDelegate(this)
        val adapter = DelegationAdapter()
        adapter.delegatesManager.addDelegate(gophersDelegate)
        rvGophers.layoutManager = linearLayoutManager
        rvGophers.adapter = adapter
        rvGophers.addItemDecoration(ItemDecorator())
        rvGophers.postDelayed(
            { rvGophers.smoothScrollToPosition(STOP_POSITION) },
            10
        )
        val gophers = resources.obtainTypedArray(R.array.gophers)
        val list = mutableListOf<Int>()
        for (i in 0 until gophers.length()) {
            list.add(gophers.getResourceId(i, -1))
        }
        adapter.items = list
    }

    companion object {
        const val STOP_POSITION = 10
        fun newInstance(context: Context): Intent = Intent(context, GophersActivity::class.java)
    }
}

