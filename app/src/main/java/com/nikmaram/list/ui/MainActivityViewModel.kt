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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
@HiltViewModel
class MainActivityViewModel @Inject constructor(private val photoPagingSource: PhotoPagingSource): ViewModel() {

    fun getListData(): Flow<PagingData<Photo>> {
        return Pager (config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {photoPagingSource}).flow.cachedIn(viewModelScope)
    }
}