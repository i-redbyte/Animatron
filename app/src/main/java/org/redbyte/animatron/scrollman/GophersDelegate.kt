package org.redbyte.animatron.scrollman

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import org.redbyte.animatron.R

class GophersDelegate(context: Context) :
    AbsListItemAdapterDelegate<Drawable, Any, GophersDelegate.Holder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun isForViewType(item: Any, items: List<Any>, position: Int): Boolean =
        item is Drawable

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        val view = inflater.inflate(R.layout.item_image, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(item: Drawable, holder: Holder, payloads: List<Any>) {
        holder.ivPicture.setImageDrawable(item)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPicture: ImageView = itemView as ImageView
    }
}