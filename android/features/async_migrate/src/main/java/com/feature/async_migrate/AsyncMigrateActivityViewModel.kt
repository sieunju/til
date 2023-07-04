package com.feature.async_migrate

import androidx.lifecycle.viewModelScope
import com.feature.async_migrate.usecase.GetGoodsUseCaseCo
import com.feature.async_migrate.usecase.GetGoodsUseCaseRx
import com.hmju.core.model.base.getOrNull
import com.hmju.core.model.base.onError
import com.hmju.core.model.base.onSuccess
import com.hmju.core.model.params.GoodsParamMap
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.lifecycle.OnCreated
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2023/05/01
 */
@HiltViewModel
class AsyncMigrateActivityViewModel @Inject constructor(
    private val getGoodsUseCaseRx: GetGoodsUseCaseRx,
    private val getGoodsUseCaseCo: GetGoodsUseCaseCo,
    private val apiService: ApiService
) : ActivityViewModel() {

    @OnCreated
    fun start() {
        val queryMap = GoodsParamMap()
        viewModelScope.launch {
            val list = getGoodsUseCaseCo(queryMap)
            Timber.d("List ${list.size}")
        }
    }

    @OnCreated
    fun test() {
        viewModelScope.launch {
            val queryMap = GoodsParamMap()
            apiService.fetchCoGoods(queryMap)
                .onSuccess {
                    Timber.d("Success $it")
                }.onError {
                    Timber.d("Error $it")
                }

            queryMap.pageNo = 100
            val res = apiService.fetchCoGoods(queryMap).getOrNull()
            Timber.d("Response $res")
        }
    }
}
