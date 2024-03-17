package com.features.base_mvvm_bottom_sheet.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.features.base_mvvm_bottom_sheet.usecase.GetGoodsUseCase
import com.hmju.core.models.params.PagingQueryParams
import com.hmju.core.ui.base.FragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/04/18
 */
@HiltViewModel
class ChildBottomSheetRedViewModel @Inject constructor(
    private val getGoodsUseCase: GetGoodsUseCase
) : FragmentViewModel() {

    private val _longText: MutableLiveData<String> by lazy { MutableLiveData() }
    val longText: LiveData<String> get() = _longText

    override fun onDirectViewCreated() {
        super.onDirectViewCreated()
        startAddLike()
    }

    private fun startAddLike() {
        viewModelScope.launch {
            val text = getGoodsUseCase(PagingQueryParams()).joinToString(",")
            _longText.value = text
        }
    }
}
