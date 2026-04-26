package com.features.activity_result.result_callback

import androidx.lifecycle.MutableLiveData
import com.hmju.core.ui.base.ActivityViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Description : 
 *
 * Created by juhongmin on 2026. 4. 26.
 */
@HiltViewModel
internal class ActivityResultRootViewModel @Inject constructor(

) : ActivityViewModel() {
    val resultText = MutableLiveData("Result: -")
}