package me.naingaungluu.codetest.ui

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Set image url binding adapter
 *
 * @param imageView
 * @param url
 */
@BindingAdapter("imageUrl")
fun setImageUrl(view : ImageView , url : String?) {

    url?.let{
        Glide.with(view.context).load(it).into(view)
    }
}

@BindingAdapter("src")
fun setImageResource(view : ImageView, resourceId : Int?){
    resourceId?.let{
        Glide.with(view.context).load(it).into(view)
    }
}