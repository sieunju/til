package com.feature.async_migrate

import androidx.lifecycle.viewModelScope
import com.feature.async_migrate.usecase.GetGoodsUseCaseCo
import com.feature.async_migrate.usecase.GetGoodsUseCaseRx
import com.hmju.core.model.base.getOrNull
import com.hmju.core.model.base.onError
import com.hmju.core.model.base.onSuccess
import com.hmju.core.model.params.GoodsParameter
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.lifecycle.OnCreated
import dagger.hilt.android.lifecycle.HiltViewModel
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
        val queryMap = GoodsParameter()
        viewModelScope.launch {
            val list = getGoodsUseCaseCo(queryMap)
            Timber.d("List ${list.size}")
        }
    }

    @OnCreated
    fun test() {
        viewModelScope.launch {
            val params = GoodsParameter()
            apiService.fetchCoGoods(params.getQueryParameter())
                .onSuccess {
                    Timber.d("Success $it")
                }.onError {
                    Timber.d("Error $it")
                }

            params.pageNo = 100
            val res = apiService.fetchCoGoods(params.getQueryParameter()).getOrNull()
            Timber.d("Response $res")
        }
    }
}
