package com.hmju.network.impl

import com.hmju.core.data.model.base.JSendListWithMeta
import com.hmju.core.data.model.meta.CustomMetaEntity
import com.hmju.network.ErrorHandlingApiService
import com.hmju.network.JSendApiService
import com.hmju.core.data.repository.ErrorHandlingRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/05/12
 */
internal class ErrorHandlingRepositoryImpl @Inject constructor(
    private val apiService: ErrorHandlingApiService,
    private val jsendApiService: JSendApiService
) : ErrorHandlingRepository {

    override fun getError505(): Single<String> {
        return apiService.getError505()
    }

    override fun postError505(): Single<String> {
        return apiService.postError505()
    }

    override fun getError404(): Single<String> {
        return apiService.getError404()
    }

    override fun postError404(): Single<String> {
        return apiService.postError404()
    }

    override fun fetchJSendListWithMeta(): Single<JSendListWithMeta<String, CustomMetaEntity>> {
        return jsendApiService.fetchSimpleJSendListMeta()
    }
}
