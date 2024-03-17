package com.hmju.core.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader.Factory
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import timber.log.Timber
import java.io.InputStream

/**
 * Description : 이미지 로더 모듈
 *
 * Created by juhongmin on 3/9/24
 */
@Suppress("unused")
@GlideModule
class GlideModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        val client = OkHttpClient.Builder()
            // .addInterceptor(TrackingHttpInterceptor())
            .addInterceptor { chain ->
                val req = chain.request()
                val res = chain.proceed(req)

                if (!res.isSuccessful) {
                    Timber.tag("ImageLoader").d("Error Url ${req.url}")
                }
                return@addInterceptor res
            }
            .build()
        registry.replace(GlideUrl::class.java, InputStream::class.java, Factory(client))
    }
}