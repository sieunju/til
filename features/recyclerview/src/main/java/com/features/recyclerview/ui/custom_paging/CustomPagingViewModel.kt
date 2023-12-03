package com.features.recyclerview.ui.custom_paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core.ui.livedata.ListLiveData
import com.hmju.core.ui.paging.PagingModel
import com.features.recyclerview.usecase.GetGoodsUseCase
import com.hmju.core.model.goods.GoodsEntity
import com.hmju.core.model.params.GoodsParameter
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

    private val _dataList: ListLiveData<GoodsEntity> by lazy { ListLiveData() }
    val dataList: ListLiveData<GoodsEntity> get() = _dataList

    val pagingModel: PagingModel by lazy { PagingModel() }
    private val queryMap: GoodsParameter by lazy { GoodsParameter() }

    fun start() {
        queryMap.tempQueryList = listOf("HIHIHI", "안녕하세요", "반갑습니다.")
        queryMap.tempQueryString = listOf("NO", "전체", "모던&포멀")
        getGoodsUseCase(queryMap)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _dataList.clear()
                _dataList.addAll(it)
            }, {
                Timber.e("Error $it")
            }).addTo(compositeDisposable)
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
