package com.features.rv_refactor_diff_util.ui

import androidx.lifecycle.viewModelScope
import com.features.rv_refactor_diff_util.ApiService
import com.features.rv_refactor_diff_util.models.ui.GoodsModel
import com.features.rv_refactor_diff_util.models.ui.GoodsOneModel
import com.features.rv_refactor_diff_util.models.ui.GoodsTwoModel
import com.features.rv_refactor_diff_util.usecase.GetGoodsUseCase
import com.hmju.core.models.params.PagingQueryParams
import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core.ui.livedata.ListLiveData
import com.hmju.core.ui.paging.PagingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

/**
 * Description :
 *
 * Created by juhongmin on 3/9/24
 */
@HiltViewModel
class RefactorDiffUtilViewModel @Inject constructor(
    private val getGoodsCoUseCase: GetGoodsUseCase,
    private val apiService: ApiService
) : FragmentViewModel() {

    private val _dataList: ListLiveData<Any> by lazy { ListLiveData() }
    val dataList: ListLiveData<Any> get() = _dataList

    val pagingModel: PagingModel by lazy { PagingModel() }
    private val queryMap: PagingQueryParams by lazy { PagingQueryParams() }

    override fun onDirectViewCreated() {
        super.onDirectViewCreated()
        start()
        startCo()
    }

    private fun start() {
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

    private fun startCo() {
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
                flowOf(apiService.fetchJSendList())
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

    private fun List<GoodsModel>.toUiModel(): List<Any> {
        val list = mutableListOf<Any>()
        this.forEach {
            if (Random.nextBoolean()) {
                list.add(GoodsOneModel(it))
            } else {
                list.add(GoodsTwoModel(it))
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
                        GoodsOneModel(it)
                    } else {
                        GoodsTwoModel(it)
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
        return if (oldItem is GoodsOneModel && newItem is GoodsOneModel) {
            oldItem.id == newItem.id
        } else if (oldItem is GoodsTwoModel && newItem is GoodsTwoModel) {
            oldItem.id == newItem.id
        } else {
            false
        }
    }

    /**
     * 좀더 디테일 하게 비교처리하는 함수
     */
    fun onContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return if (oldItem is GoodsOneModel && newItem is GoodsOneModel) {
            oldItem == newItem
        } else if (oldItem is GoodsTwoModel && newItem is GoodsTwoModel) {
            oldItem == newItem
        } else {
            false
        }
    }
}
