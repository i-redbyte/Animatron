package org.redbyte.animatron.scrollman

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_auto_go.*
import org.redbyte.animatron.R
import org.redbyte.animatron.base.DelegationAdapter

class ScrollmanActivity : AppCompatActivity() {

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
        fun open(context: Context): Intent = Intent(context, ScrollmanActivity::class.java)
    }
}

