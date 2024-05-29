package com.hmju.core.glide

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import hmju.http.tracking_interceptor.TrackingHttpInterceptor
import okhttp3.OkHttpClient
import java.io.InputStream

/**
 * Description : 이미지 로더 모듈
 * @see <a href="https://github.com/bumptech/glide/issues/5328">v4.16.0 안먹히는 이슈</a>
 * @see <a href="https://github.com/bumptech/glide/pull/5344">해결은 했지만 릴리즈는 안나옴</a>
 * Created by juhongmin on 3/9/24
 */
@GlideModule
class GlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.setLogLevel(Log.VERBOSE)
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val client = OkHttpClient.Builder()
            .addInterceptor(TrackingHttpInterceptor())
            .build()
        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(client)
        )
    }
}