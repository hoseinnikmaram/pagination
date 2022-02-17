package com.nikmaram.list.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nikmaram.list.model.Photo
import com.nikmaram.list.model.providePhotos

class PhotoPagingSource() : PagingSource<Int,Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = providePhotos(nextPage)

            LoadResult.Page(data = response,
                prevKey = if (nextPage == FIRST_PAGE_INDEX) null else nextPage - 1,
                nextKey = nextPage + 1)
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}