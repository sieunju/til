package com.hmju.core.rxbus

/**
 * Description :
 *
 * Created by juhongmin on 2022/08/23
 */
data class LoginEvent(
    val isChanged: Boolean,
    val token: String
) : RxBusEvent()
