package org.redbyte.animatron.base.extensions

import android.content.Context

fun Int.resDrawableArray(context: Context, index: Int, block: (drawableResId: Int) -> Unit) {
    val array = context.resources.obtainTypedArray(this)
    block(array.getResourceId(index, -1))
    array.recycle()
}
fun <T> List<List<T>>.transpose(): List<List<T>> {
    if (isEmpty()) return emptyList()
    val width = first().size
    val height = size
    return List(width) { col ->
        List(height) { row ->
            this[row][col]
        }
    }
}