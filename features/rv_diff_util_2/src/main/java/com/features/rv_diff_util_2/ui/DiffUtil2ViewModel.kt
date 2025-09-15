package com.features.rv_diff_util_2.ui

import androidx.lifecycle.viewModelScope
import com.features.rv_diff_util_2.models.ui.GoodsOneUiModel
import com.features.rv_diff_util_2.models.ui.GoodsTwoUiModel
import com.features.rv_diff_util_2.usecase.GetGoodsUseCase
import com.hmju.core.models.params.PagingQueryParams
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core.ui.livedata.ListLiveData
import com.hmju.core.ui.paging.PagingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

/**
 * Description :
 *
 * Created by juhongmin on 3/10/24
 */
@HiltViewModel
class DiffUtil2ViewModel @Inject constructor(
    private val getGoodsUseCase: GetGoodsUseCase
) : FragmentViewModel() {

    private val _dataList: ListLiveData<BaseUiModel> by lazy { ListLiveData() }
    val dataList: ListLiveData<BaseUiModel> get() = _dataList
    val pagingModel: PagingModel by lazy { PagingModel() }
    private val queryMap: PagingQueryParams by lazy { PagingQueryParams() }

    override fun onDirectViewCreated() {
        super.onDirectViewCreated()
        fetchGoods()
    }

    fun onStart() {
        fetchGoods()
    }

    private fun fetchGoods() {
        Timber.d("RequestParams $queryMap")
        viewModelScope.launch {
            pagingModel.isLoading = true
            val list = getGoodsUseCase(queryMap).map {
                if (Random.nextBoolean()) {
                    GoodsOneUiModel(it)
                } else {
                    GoodsTwoUiModel(it)
                }
            }
            queryMap.pageNo ++
            pagingModel.isLast = list.isEmpty()
            pagingModel.isLoading = false
            _dataList.addAll(list)
        }
    }
}
