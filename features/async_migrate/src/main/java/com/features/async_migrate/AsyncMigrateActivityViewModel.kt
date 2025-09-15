package com.features.async_migrate

import androidx.lifecycle.viewModelScope
import com.features.async_migrate.usecase.GetGoodsUseCaseCo
import com.features.async_migrate.usecase.GetGoodsUseCaseRx
import com.hmju.core.models.base.getOrNull
import com.hmju.core.models.base.onError
import com.hmju.core.models.base.onSuccess
import com.hmju.core.models.params.PagingQueryParams
import com.hmju.core.ui.base.ActivityViewModel
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
    private val getGoodsUseCaseCo: GetGoodsUseCaseCo,
    private val apiService: ApiService
) : ActivityViewModel() {

    override fun onDirectCreate() {
        super.onDirectCreate()
        start()
        test()
    }

    private fun start() {
        val queryMap = PagingQueryParams()
        viewModelScope.launch {
            val list = getGoodsUseCaseCo(queryMap)
            Timber.d("List ${list.size}")
        }
    }

    private fun test() {
        viewModelScope.launch {
            val params = PagingQueryParams()
            apiService.fetchCoGoods(params.getQueryMap())
                .onSuccess {
                    Timber.d("Success $it")
                }.onError {
                    Timber.d("Error $it")
                }

            params.pageNo = 100
            val res = apiService.fetchCoGoods(params.getQueryMap()).getOrNull()
            Timber.d("Response $res")
        }
    }
}
