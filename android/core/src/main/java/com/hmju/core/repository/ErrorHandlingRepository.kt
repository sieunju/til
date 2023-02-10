package com.hmju.core.repository

import com.hmju.core.model.base.JSendListWithMeta
import com.hmju.core.model.meta.CustomMetaEntity
import io.reactivex.rxjava3.core.Single

/**
 * Description : 에러 헨들링 전용 Repository Interface
 *
 * Created by juhongmin on 2022/05/12
 */
interface ErrorHandlingRepository {
    fun getError505(): Single<String>
    fun postError505(): Single<String>
    fun getError404(): Single<String>
    fun postError404(): Single<String>
    fun fetchJSendListWithMeta(): Single<JSendListWithMeta<String, CustomMetaEntity>>
}