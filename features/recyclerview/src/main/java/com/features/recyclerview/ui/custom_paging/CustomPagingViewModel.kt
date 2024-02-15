package com.features.recyclerview.ui.custom_paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.features.recyclerview.models.ui.GoodsModel
import com.features.recyclerview.usecase.GetGoodsUseCase
import com.hmju.core.models.params.PagingQueryParams
import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core.ui.livedata.ListLiveData
import com.hmju.core.ui.paging.PagingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class CustomPagingViewModel @Inject constructor(
    private val getGoodsUseCase: GetGoodsUseCase
) : FragmentViewModel() {

    private val _pageNo: MutableLiveData<String> by lazy { MutableLiveData() }
    val pageNo: LiveData<String> get() = _pageNo

    private val _dataList: ListLiveData<GoodsModel> by lazy { ListLiveData() }
    val dataList: ListLiveData<GoodsModel> get() = _dataList

    val pagingModel: PagingModel by lazy { PagingModel() }
    private val queryMap: PagingQueryParams by lazy { PagingQueryParams() }

    fun start() {
        getGoodsUseCase(queryMap)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                _dataList.clear()
                _dataList.addAll(it)
            }
            .doOnError { Timber.e("Error $it") }
            .subscribe().addTo(compositeDisposable)
    }

    fun onLoadNextPage() {
        Timber.d("Call onLoadNextPage $queryMap.pageNo")
        getGoodsUseCase(queryMap)
            .doOnSubscribe { pagingModel.isLoading = true }
            .delay(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d("List ${it.size}")
                queryMap.pageNo++
                pagingModel.isLast = it.isEmpty()
                pagingModel.isLoading = false
                _dataList.addAll(it)
            }, {
                pagingModel.isLast = true
            }).addTo(compositeDisposable)
    }
}
