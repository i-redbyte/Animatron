package org.redbyte.animatron.scrollman

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.redbyte.animatron.base.extensions.dp

class ItemDecorator : RecyclerView.ItemDecoration() {
    companion object {
        val PADDING = 16.dp()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val startEndPadding = PADDING / 2
        outRect.set(Rect(
            PADDING,
            startEndPadding,
            PADDING,
            startEndPadding
        ))
    }

    private fun isVertical(parent: RecyclerView): Boolean =
        (parent.layoutManager as? LinearLayoutManager)?.orientation == LinearLayoutManager.VERTICAL

}