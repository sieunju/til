package com.features.base_mvvm.ui.refactor_base.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.features.base_mvvm.usecase.GetGoodsUseCase
import com.features.core_ui.base.FragmentViewModel
import com.features.core_ui.lifecycle.OnViewCreated
import com.hmju.core.data.model.params.GoodsParamMap
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
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

    @OnViewCreated
    fun startAddLike() {
        val queryMap = GoodsParamMap()
        getGoodsUseCase(queryMap)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _longText.value = it.joinToString(",")
            }, {
            }).addTo(compositeDisposable)
    }
}
