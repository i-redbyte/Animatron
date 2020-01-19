package org.redbyte.animatron.base.extensions

import android.content.Context

fun Int.resDrawableArray(context: Context, index: Int, block: (drawableResId: Int) -> Unit) {
    val array = context.resources.obtainTypedArray(this)
    block(array.getResourceId(index, -1))
    array.recycle()
}
