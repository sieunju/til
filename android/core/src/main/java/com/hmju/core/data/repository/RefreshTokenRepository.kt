package com.hmju.core.data.repository

import com.hmju.core.data.model.auth.TokenEntity
import com.hmju.core.data.model.base.JSendObj
import io.reactivex.rxjava3.core.Single

interface RefreshTokenRepository {
    fun fetch(): Single<JSendObj<TokenEntity>>
}