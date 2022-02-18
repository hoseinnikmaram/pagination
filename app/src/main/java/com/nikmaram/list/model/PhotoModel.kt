package com.nikmaram.list.model

import com.nikmaram.list.R
import com.nikmaram.list.util.PAGE_SIZE

data class Photo(val image:Int,val id:Int,val page: Int)

fun providePhotos(page:Int): MutableList<Photo> {
    val photoList = mutableListOf<Photo>()
    val startIndex = page * PAGE_SIZE
    val endIndex = startIndex + PAGE_SIZE
    for (i in startIndex..endIndex){
        photoList.add(Photo(R.drawable.cory,i,page))
    }
    return photoList
}