package com.features.recyclerview.ui.diffutil_refactor

import androidx.lifecycle.viewModelScope
import com.features.recyclerview.ApiService
import com.features.recyclerview.model.GoodsOneUiModel
import com.features.recyclerview.model.GoodsTwoUiModel
import com.features.recyclerview.usecase.GetGoodsCoUseCase
import com.features.recyclerview.usecase.GetGoodsUseCase
import com.hmju.core.model.goods.GoodsEntity
import com.hmju.core.model.params.GoodsParamMap
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core.ui.lifecycle.OnViewCreated
import com.hmju.core.ui.livedata.ListLiveData
import com.hmju.core.ui.paging.PagingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RefactorDiffUtilViewModel @Inject constructor(
    private val getGoodsCoUseCase: GetGoodsCoUseCase,
    private val apiService: ApiService
) : FragmentViewModel() {

    private val _dataList: ListLiveData<BaseUiModel> by lazy { ListLiveData() }
    val dataList: ListLiveData<BaseUiModel> get() = _dataList

    val pagingModel: PagingModel by lazy { PagingModel() }
    private val queryMap: GoodsParamMap by lazy { GoodsParamMap() }

    @OnViewCreated
    fun start() {
        viewModelScope.launch(Dispatchers.Main) {
            flowOf(getGoodsCoUseCase(queryMap, 0))
                .onStart { pagingModel.isLoading = true }
                .map { it.toUiModel() }
                .flowOn(Dispatchers.IO)
                .onEach {
                    _dataList.addAll(it)
                    queryMap.pageNo++
                    pagingModel.isLoading = false
                    pagingModel.isLast = it.isEmpty()
                }
                .catch {
                    Timber.d("ERROR $it")
                    pagingModel.isLast = true
                }
                .collect()
        }
    }

    @OnViewCreated
    fun startCo() {
//        viewModelScope.launch {
//            Timber.d("로루틴 테스트 스타일 시작")
//            val job3 = testJob3()
//            // await 하는 순간 Deferred 작업 끝날때까지 기다림
//            val list = job3.await()
//            Timber.d("로루틴 테스트 스타일 끝")
//        }

        viewModelScope.launch(Dispatchers.Main) {
            Timber.d("로루틴 테스트 스타일 시작")
            testJob1()
            testJob2()
            Timber.d("코루틴 시작 끝")
        }
    }

    private fun testJob1(): Job {
        return viewModelScope.launch(Dispatchers.Main) {
            return@launch combine(
                flowOf(getGoodsCoUseCase(queryMap, 300)),
                flowOf(apiService.fetchCoJSendList())
            ) { list, jsend ->
                Timber.d("코루틴 콤바인 ${list.size} $jsend")
                return@combine list to jsend
            }
                .map { "${it.first.size}_${it.second}" }
                .onEach { Timber.d("하이루 $it") }
                .catch {
                    Timber.d("ERROR $it")
                }
                .collect()
        }
    }

    private fun testJob2(): Job {
        return viewModelScope.launch(Dispatchers.Main) {
            (1..10)
                .asFlow()
                .onEach { delay(300) }
                .map { "${System.currentTimeMillis()}_$it" }
                .onEach { Timber.d("TEST $it") }
                .collect()
        }
    }


    private fun testJob3(): Deferred<List<String>> {
        return CoroutineScope(Dispatchers.IO).async {
            val list = mutableListOf<String>()
            for (idx in 0 until 10) {
                list.add("TEST ${System.currentTimeMillis()}_$idx")
                delay(1000)
                Timber.d("실행실행 ${list.size}")
            }
            return@async list
        }
    }

    private fun List<GoodsEntity>.toUiModel(): List<BaseUiModel> {
        val list = mutableListOf<BaseUiModel>()
        this.forEach {
            if (Random.nextBoolean()) {
                list.add(GoodsOneUiModel(it))
            } else {
                list.add(GoodsTwoUiModel(it))
            }
        }
        return list
    }

    /**
     * Load Next Page
     */
    fun onLoadNextPage() {
        viewModelScope.launch(Dispatchers.Main) {
            pagingModel.isLoading = true
            val uiList = getGoodsCoUseCase(queryMap, 0)
                .map {
                    if (Random.nextBoolean()) {
                        GoodsOneUiModel(it)
                    } else {
                        GoodsTwoUiModel(it)
                    }
                }
            _dataList.addAll(uiList)
            queryMap.pageNo++
            pagingModel.isLoading = false
            pagingModel.isLast = uiList.isEmpty()
        }
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