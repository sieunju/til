package com.hmju.compose_permissions_result.screen

import android.Manifest
import androidx.lifecycle.ViewModel
import com.hmju.core.permission.PermissionProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 4/3/24
 */
@HiltViewModel
class PermissionViewModel @Inject constructor(
    private val provider: PermissionProvider
) : ViewModel() {
    fun isAllGrated(result: Map<String, Boolean>): Boolean {
        return provider.isAllGrated(result)
    }

    fun isAllGrated(result: List<String>): Boolean {
        return provider.isAllGrated(result)
    }

    fun moveToSetting() {
        provider.moveToSetting()
    }

    fun getPermissionList() : Array<String> {
        return arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA
        )
    }
}
