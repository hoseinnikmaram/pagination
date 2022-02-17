package com.nikmaram.list.util

import android.net.Uri
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