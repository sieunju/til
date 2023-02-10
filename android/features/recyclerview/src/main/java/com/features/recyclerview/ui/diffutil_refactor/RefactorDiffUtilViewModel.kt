package com.features.recyclerview.ui.diffutil_refactor

import androidx.lifecycle.viewModelScope
import com.features.recyclerview.model.GoodsOneUiModel
import com.features.recyclerview.model.GoodsTwoUiModel
import com.features.recyclerview.usecase.GetGoodsCoUseCase
import com.features.recyclerview.usecase.GetGoodsUseCase
import com.hmju.core.model.base.ApiResponse
import com.hmju.core.model.params.GoodsParamMap
import com.hmju.core.repository.GoodsRepository
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core.ui.lifecycle.OnViewCreated
import com.hmju.core.ui.livedata.ListLiveData
import com.hmju.core.ui.paging.PagingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RefactorDiffUtilViewModel @Inject constructor(
    private val getGoodsUseCase: GetGoodsUseCase,
    private val getGoodsCoUseCase: GetGoodsCoUseCase,
    private val repository: GoodsRepository
) : FragmentViewModel() {

    private val _dataList: ListLiveData<BaseUiModel> by lazy { ListLiveData() }
    val dataList: ListLiveData<BaseUiModel> get() = _dataList

    val pagingModel: PagingModel by lazy { PagingModel() }
    private val queryMap: GoodsParamMap by lazy { GoodsParamMap() }

    @OnViewCreated
    fun start() {
        getGoodsUseCase(queryMap)
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
                _dataList.clear()
                _dataList.addAll(it)
            }, {

            }).addTo(compositeDisposable)
    }

    @OnViewCreated
    fun startCo() {
        viewModelScope.launch(Dispatchers.Main) {
            Timber.d("Start!!!!!")
            val join  = asyncTest()
            Timber.d("End!!!!!")
            join.start()
//            val list = getGoodsCoUseCase(queryMap)
//                .map {
//                    if (Random.nextBoolean()) {
//                        GoodsOneUiModel(it)
//                    } else {
//                        GoodsTwoUiModel(it)
//                    }
//                }
//            _dataList.clear()
//            _dataList.addAll(list)
//            queryMap.pageNo++
//            pagingModel.isLoading = false
        }
    }

    private fun asyncTest () : Deferred<Boolean> {
        return viewModelScope.async {
            flowOf(
                getGoodsCoUseCase(queryMap,3000),
                getGoodsCoUseCase(queryMap,1000).map { it.id })
                .onEach {
                    Timber.d("${it[0]}")
                }.launchIn(this)
            true
        }
    }

    /**
     * Load Next Page
     */
    fun onLoadNextPage() {
        viewModelScope.launch(Dispatchers.Main) {
            pagingModel.isLoading = true
            val list = getGoodsCoUseCase(queryMap,500)
                .map {
                    if (Random.nextBoolean()) {
                        GoodsOneUiModel(it)
                    } else {
                        GoodsTwoUiModel(it)
                    }
                }
            _dataList.addAll(list)
            queryMap.pageNo++
            pagingModel.isLoading = false
            pagingModel.isLast = list.isEmpty()
        }
//        getGoodsUseCase(queryMap)
//            .doOnSubscribe { pagingModel.isLoading = true }
//            .delay(500, TimeUnit.MILLISECONDS)
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
//                queryMap.pageNo++
//                pagingModel.isLast = it.isEmpty()
//                pagingModel.isLoading = false
//                _dataList.addAll(it)
//            }, {
//                pagingModel.isLast = true
//            }).addTo(compositeDisposable)
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