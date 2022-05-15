package com.hmju.domain.repository

import com.til.model.base.*
import com.til.model.test.JSendTestEntity
import io.reactivex.rxjava3.core.Single

/**
 * Description : JSend Repository
 *
 * Created by juhongmin on 2022/02/11
 */
interface JSendRepository {
    fun fetchJSend(): Single<JSendBaseResponse<JSend<JSendTestEntity>>>
    fun fetchJSendWithMeta(): Single<JSendBaseResponse<JSendWithMeta<JSendTestEntity, CustomMetaEntity>>>
    fun fetchJSendList(): Single<JSendBaseResponse<JSendList<String>>>
    fun fetchJSendListWithMeta(): Single<JSendBaseResponse<JSendListWithMeta<String, MetaEntity>>>
    fun fetchJSendListMeta(): Single<JSendListWithMeta<String, MetaEntity>>
    fun fetchSimpleJSendListMeta(): Single<JSendSimpleListWithMeta<String, CustomMetaEntity>>
}
