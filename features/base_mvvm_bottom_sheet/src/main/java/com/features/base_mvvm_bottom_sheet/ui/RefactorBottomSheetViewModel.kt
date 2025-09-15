package com.features.base_mvvm_bottom_sheet.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.features.base_mvvm_bottom_sheet.usecase.GetGoodsUseCase
import com.hmju.core.models.params.PagingQueryParams
import com.hmju.core.ui.base.BottomSheetViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

/**
 * Description :
 *
 * Created by juhongmin on 2022/04/18
 */
@HiltViewModel
class RefactorBottomSheetViewModel @Inject constructor(
    private val getGoodsUseCase: GetGoodsUseCase
) : BottomSheetViewModel() {

    private val _blueTitle: MutableLiveData<String> by lazy { MutableLiveData() }
    val blueTitle: LiveData<String> get() = _blueTitle

    private val _redTitle: MutableLiveData<String> by lazy { MutableLiveData() }
    val redTitle: LiveData<String> get() = _redTitle

    val startDismiss: MutableLiveData<Unit> by lazy { MutableLiveData() }

    override fun onDirectViewCreated() {
        super.onDirectViewCreated()
        val queryMap = PagingQueryParams()
        queryMap.pageNo = 2
        viewModelScope.launch {
            Timber.d("SUCC ${getGoodsUseCase(queryMap)}")
        }
        startBlueTitle()
        startRedTitle()
    }

    private fun startBlueTitle() {
        viewModelScope.launch {
            repeat(1000) {
                delay(1000)
                _blueTitle.value = "${Random.nextInt(1000)}_$it"
            }
        }
    }

    private fun startRedTitle() {
        viewModelScope.launch {
            repeat(1000) {
                delay(1000)
                _redTitle.value = "${Random.nextInt(1000)}_$it"
            }
        }
    }

    fun onDismiss() {
        startDismiss.value = null
    }
}
