package com.nikmaram.list.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nikmaram.list.model.Photo
import com.nikmaram.list.util.PAGE_SIZE
import com.nikmaram.list.util.PhotoPagingSource
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel : ViewModel() {

    fun getListData(): Flow<PagingData<Photo>> {
        return Pager (config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {PhotoPagingSource()}).flow.cachedIn(viewModelScope)
    }
}