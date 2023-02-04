package com.features.recyclerview.ui.diffutil_refactor

import androidx.lifecycle.viewModelScope
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core.ui.livedata.ListLiveData
import com.hmju.core.ui.paging.PagingModel
import com.features.recyclerview.model.GoodsOneUiModel
import com.features.recyclerview.model.GoodsTwoUiModel
import com.features.recyclerview.usecase.GetGoodsCoUseCase
import com.features.recyclerview.usecase.GetGoodsUseCase
import com.hmju.core.model.params.GoodsParamMap
import com.hmju.core.ui.lifecycle.OnViewCreated
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RefactorDiffUtilViewModel @Inject constructor(
    private val getGoodsUseCase: GetGoodsUseCase,
    private val getGoodsCoUseCase: GetGoodsCoUseCase
) : FragmentViewModel() {

    private val _dataList: ListLiveData<BaseUiModel> by lazy { ListLiveData() }
    val dataList: ListLiveData<BaseUiModel> get() = _dataList

    val pagingModel: PagingModel by lazy { PagingModel() }
    private val queryMap: GoodsParamMap by lazy { GoodsParamMap() }

//    @OnViewCreated
//    fun start() {
//        getGoodsUseCase(queryMap)
//            .map { list ->
//                val uiList = mutableListOf<BaseUiModel>()
//                list.forEach {
//                    uiList.add(
//                        if (Random.nextBoolean()) {
//                            GoodsOneUiModel(it)
//                        } else {
//                            GoodsTwoUiModel(it)
//                        }
//                    )
//                }
//                uiList
//            }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                _dataList.clear()
//                _dataList.addAll(it)
//            }, {
//
//            }).addTo(compositeDisposable)
//    }

    @OnViewCreated
    fun startCo(){
        viewModelScope.launch(Dispatchers.Main) {
            val list = getGoodsCoUseCase(queryMap)
            Timber.d("List $list")
        }
    }

    /**
     * Load Next Pagea
     */
    fun onLoadNextPage() {
        getGoodsUseCase(queryMap)
            .doOnSubscribe { pagingModel.isLoading = true }
            .delay(500, TimeUnit.MILLISECONDS)
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
                uiList
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                queryMap.pageNo++
                pagingModel.isLast = it.isEmpty()
                pagingModel.isLoading = false
                _dataList.addAll(it)
            }, {
                pagingModel.isLast = true
            }).addTo(compositeDisposable)
    }

    /**
     * 간단한 아이디 값정보만 비교처리하는 함수
     */
    fun onItemTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is GoodsOneUiModel && newItem is GoodsOneUiModel) {
            oldItem.item.id == newItem.item.id
        } else if (oldItem is GoodsTwoUiModel && newItem is GoodsTwoUiModel) {
            oldItem.item.id == newItem.item.id
        } else {
            false
        }
    }

    /**
     * 좀더 디테일 하게 비교처리하는 함수
     */
    fun onContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is GoodsOneUiModel && newItem is GoodsOneUiModel) {
            oldItem.item == newItem.item
        } else if (oldItem is GoodsTwoUiModel && newItem is GoodsTwoUiModel) {
            oldItem.item == newItem.item
        } else {
            false
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}