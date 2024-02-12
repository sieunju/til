package com.features.recyclerview.ui.diffutil_v2

import com.features.recyclerview.models.GoodsOneUiModel
import com.features.recyclerview.models.GoodsTwoUiModel
import com.features.recyclerview.usecase.GetGoodsUseCase
import com.hmju.core.model.params.GoodsParameter
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core.ui.livedata.ListLiveData
import com.hmju.core.ui.paging.PagingModel
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
    private val queryMap: GoodsParameter by lazy { GoodsParameter() }

    override fun onDirectViewCreated() {
        super.onDirectViewCreated()
        onStart()
    }

    fun onStart() {
        fetchGoods()
    }

    private fun fetchGoods() {
        getGoodsUseCase(queryMap)
            .doOnSubscribe { pagingModel.isLoading = true }
            .map { list ->
                list.map {
                    if (Random.nextBoolean()) {
                        GoodsOneUiModel(it)
                    } else {
                        GoodsTwoUiModel(it)
                    }
                }
            }.observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                queryMap.pageNo++
                pagingModel.isLast = it.isEmpty()
                pagingModel.isLoading = false
                _dataList.addAll(it)
            }
            .doOnError { pagingModel.isLast = true }
            .subscribe().addTo(compositeDisposable)
    }
}
