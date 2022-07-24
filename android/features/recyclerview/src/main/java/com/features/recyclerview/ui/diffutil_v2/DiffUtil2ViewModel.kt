package com.features.recyclerview.ui.diffutil_v2

import com.features.core_ui.BaseUiModel
import com.features.recyclerview.model.GoodsOneUiModel
import com.features.recyclerview.model.GoodsTwoUiModel
import com.hmju.core.FragmentViewModel
import com.hmju.core.livedata.ListLiveData
import com.hmju.core.paging.PagingModel
import com.hmju.domain.usecase.GetGoodsUseCase
import com.hmju.lifecycle.OnViewCreated
import com.til.model.params.GoodsParamMap
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class DiffUtil2ViewModel @Inject constructor(
    private val getGoodsUseCase: GetGoodsUseCase
) : FragmentViewModel() {

    private val _dataList: ListLiveData<BaseUiModel> by lazy { ListLiveData() }
    val dataList: ListLiveData<BaseUiModel> get() = _dataList

    val pagingModel: PagingModel by lazy { PagingModel() }
    private val queryMap: GoodsParamMap by lazy { GoodsParamMap() }

    @OnViewCreated
    fun onStart() {
        fetchGoods()
    }

    private fun fetchGoods() {
        getGoodsUseCase(queryMap)
            .doOnSubscribe { pagingModel.isLoading = true }
            .map { list ->
                val uiList = mutableListOf<BaseUiModel>()
                list.forEach {
                    uiList.add(
                        if (Random.nextBoolean()) {
                            GoodsOneUiModel(it)
                        } else {
                            GoodsTwoUiModel(it)
                        }
                    )
                }
                return@map uiList
            }.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                queryMap.pageNo++
                pagingModel.isLast = it.isEmpty()
                pagingModel.isLoading = false
                _dataList.addAll(it)
            }, {
                pagingModel.isLast = true
            }).addTo(compositeDisposable)
    }
}
