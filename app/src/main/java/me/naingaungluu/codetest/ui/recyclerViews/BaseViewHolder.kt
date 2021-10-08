package me.naingaungluu.codetest.ui.recyclerViews

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<W>(itemView : View) : RecyclerView.ViewHolder(itemView) {

    var mData : W? = null
        set(value) {
            field = value
            value?.let {
                setData(it)
            }
        }

    abstract fun setData(data : W)
}