package me.naingaungluu.codetest.ui.recyclerViews

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : BaseViewHolder<W>, W>() : RecyclerView.Adapter<T>() {

    protected var mData : MutableList<W> = ArrayList()

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.mData = mData[position]
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    fun setNewData(newData : List<W>){
        mData = newData.toMutableList()
        notifyDataSetChanged()
    }

}