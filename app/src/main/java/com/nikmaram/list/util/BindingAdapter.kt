package com.nikmaram.list.util

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageURL")
fun ImageView.setImageURL(imageURL:Any){
    Glide.with(this)
        .load(imageURL)
        .apply(RequestOptions.overrideOf(1000, 1000))
        .into(this)
}
@BindingAdapter("android:layout_marginEnd")
fun setMarginEnd(view: View, margin: Float) {
    val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.marginStart = margin.toInt()
    view.layoutParams = layoutParams
}