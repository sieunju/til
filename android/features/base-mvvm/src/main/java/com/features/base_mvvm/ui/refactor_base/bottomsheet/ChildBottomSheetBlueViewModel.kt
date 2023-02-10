package com.features.base_mvvm.ui.refactor_base.bottomsheet

import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core.ui.lifecycle.OnViewCreated
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/04/18
 */
@HiltViewModel
class ChildBottomSheetBlueViewModel @Inject constructor(

) : FragmentViewModel() {

    @OnViewCreated
    fun getGoodsTest() {

    }
}
