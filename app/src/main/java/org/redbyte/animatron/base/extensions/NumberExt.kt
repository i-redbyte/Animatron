package org.redbyte.animatron.base.extensions

import org.redbyte.animatron.App
import org.redbyte.animatron.R

fun Int.dp(): Int {
    if (this == 0) {
        return 0
    }
    val metrics = App.context.resources?.displayMetrics
    val err = App.context.resources.getString(R.string.error_null_metrics)
    require(metrics != null) { err }
    return (this * metrics.density).toInt()
}
