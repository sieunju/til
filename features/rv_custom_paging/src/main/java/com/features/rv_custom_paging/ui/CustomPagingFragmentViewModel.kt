package com.features.rv_custom_paging.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.features.rv_custom_paging.models.ui.GoodsUiModel
import com.features.rv_custom_paging.usecase.GetGoodsUseCase
import com.hmju.core.models.params.PagingQueryParams
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core.ui.livedata.ListLiveData
import com.hmju.core.ui.paging.PagingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.delayFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Description : ViewModel
 *
 * Created by juhongmin on 2/15/24
 */
@HiltViewModel
class CustomPagingFragmentViewModel @Inject constructor(
    private val getGoodsUseCase: GetGoodsUseCase
) : FragmentViewModel() {

    private val _pageNo: MutableLiveData<String> by lazy { MutableLiveData() }
    val pageNo: LiveData<String> get() = _pageNo
    val pagingModel: PagingModel by lazy { PagingModel() }
    private val _dataList: ListLiveData<BaseUiModel> by lazy { ListLiveData() }
    val dataList: ListLiveData<BaseUiModel> get() = _dataList
    private val queryParams: PagingQueryParams by lazy { PagingQueryParams() }

    override fun onDirectViewCreated() {
        super.onDirectViewCreated()
        req()
    }

    private fun req() {
        viewModelScope.launch {
            pagingModel.isLoading = true
            delay(1000)
            val list = getGoodsUseCase(queryParams)
                .map { GoodsUiModel(it) }
            _dataList.addAll(list)
            queryParams.pageNo++
            pagingModel.isLoading = false
            pagingModel.isLast = list.isEmpty()
        }
    }

    fun onLoadNextPage() {
        req()
    }
}
