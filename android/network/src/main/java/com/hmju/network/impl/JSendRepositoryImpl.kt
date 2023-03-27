package com.hmju.network.impl

import com.hmju.core.model.base.*
import com.hmju.core.model.meta.CustomMetaEntity
import com.hmju.core.model.test.JSendTestEntity
import com.hmju.core.repository.JSendRepository
import com.hmju.network.JSendApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/02/11
 */
internal class JSendRepositoryImpl @Inject constructor(
    private val apiService: JSendApiService
) : JSendRepository {
    override fun fetchJSend(): Single<JSendObj<JSendTestEntity>> {
        return apiService.fetchJSend()
    }

    override fun fetchJSendWithMeta(): Single<JSendObjWithMeta<JSendTestEntity, CustomMetaEntity>> {
        return apiService.fetchJSendWithMeta()
    }

    override fun fetchJSendList(): Single<JSendList<String>> {
        return apiService.fetchJSendList()
    }

    override fun fetchJSendListWithMeta(): Single<JSendListWithMeta<String, MetaEntity>> {
        return apiService.fetchJSendListWithMeta()
    }

    override fun fetchJSendListMeta(): Single<JSendListWithMeta<String, MetaEntity>> {
        return apiService.fetchJSendListWithMeta()
    }

    override fun fetchSimpleJSendListMeta(): Single<JSendListWithMeta<String, CustomMetaEntity>> {
        return apiService.fetchSimpleJSendListMeta()
    }

    override fun fetchErrorTest(): Single<JSendObjWithMeta<String, MetaEntity>> {
        return apiService.fetchErrorTest()
    }

    override suspend fun fetchCoJSendList(): ApiResponse<JSendList<String>> {
        return apiService.fetchCoJSendList()
    }
}
