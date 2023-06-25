package com.feature.async_migrate

import androidx.lifecycle.viewModelScope
import com.feature.async_migrate.usecase.GetGoodsUseCaseCo
import com.feature.async_migrate.usecase.GetGoodsUseCaseRx
import com.hmju.core.model.params.GoodsParamMap
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.lifecycle.OnCreated
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
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
    private val getGoodsUseCaseCo: GetGoodsUseCaseCo
) : ActivityViewModel() {

    @OnCreated
    fun start(){
        val queryMap = GoodsParamMap()
        viewModelScope.launch {
            val list = getGoodsUseCaseCo(queryMap)
            Timber.d("List ${list.size}")
        }
    }
}
