package com.features.rv_simple_like

import androidx.lifecycle.viewModelScope
import com.features.rv_simple_like.models.ui.Simple1UiModel
import com.features.rv_simple_like.models.ui.Simple2UiModel
import com.features.rv_simple_like.models.ui.Simple3UiModel
import com.features.rv_simple_like.models.ui.Simple4UiModel
import com.features.rv_simple_like.usecase.GetGoodsUseCase
import com.hmju.core.models.params.PagingQueryParams
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core.ui.livedata.ListLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 3/2/24
 */
@HiltViewModel
class SimpleLikeFragmentViewModel @Inject constructor(
    private val getGoodsUseCase: GetGoodsUseCase
) : FragmentViewModel() {

    private val oneTypeParams: PagingQueryParams by lazy { PagingQueryParams() }
    private val _oneTypeList: ListLiveData<BaseUiModel> by lazy { ListLiveData() }
    val oneTypeList: ListLiveData<BaseUiModel> get() = _oneTypeList
    private val twoTypeParams: PagingQueryParams by lazy { PagingQueryParams() }
    private val _twoTypeList: ListLiveData<BaseUiModel> by lazy { ListLiveData() }
    val twoTypeList: ListLiveData<BaseUiModel> get() = _twoTypeList

    override fun onDirectViewCreated() {
        super.onDirectViewCreated()
        reqOneType()
        reqTwoType()
    }

    private fun reqOneType() {
        viewModelScope.launch {
            oneTypeParams.pageSize = 100
            val uiList = getGoodsUseCase(oneTypeParams)
                .mapIndexed { idx, model ->
                    when(idx % 4) {
                        1 -> Simple1UiModel(model)
                        2 -> Simple2UiModel(model)
                        3 -> Simple3UiModel(model)
                        else -> Simple4UiModel(model)
                    }
                }
            _oneTypeList.addAll(uiList)
        }
    }

    private fun reqTwoType(){
        viewModelScope.launch {
            val uiList = getGoodsUseCase(twoTypeParams)
                .mapIndexed { idx, model ->
                    when(idx % 4) {
                        1 -> Simple1UiModel(model)
                        2 -> Simple2UiModel(model)
                        3 -> Simple3UiModel(model)
                        else -> Simple4UiModel(model)
                    }
                }
            _twoTypeList.addAll(uiList)
        }
    }
}
