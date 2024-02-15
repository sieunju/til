package com.features.base_mvvm.ui.refactor_base.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.features.base_mvvm.usecase.GetGoodsUseCase
import com.hmju.core.models.params.PagingParams
import com.hmju.core.ui.base.FragmentViewModel
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

    override fun onDirectViewCreated() {
        super.onDirectViewCreated()
        startAddLike()
    }

    private fun startAddLike() {
        val queryMap = PagingParams()
        getGoodsUseCase(queryMap)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { _longText.value = it.joinToString(",") }
            .subscribe().addTo(compositeDisposable)
    }
}
