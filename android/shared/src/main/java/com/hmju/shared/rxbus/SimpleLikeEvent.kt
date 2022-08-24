package com.hmju.shared.rxbus

/**
 * Description :
 *
 * Created by juhongmin on 2022/08/23
 */
data class SimpleLikeEvent(
    val isAdd: Boolean,
    val id: Long
) : RxBusEvent()
