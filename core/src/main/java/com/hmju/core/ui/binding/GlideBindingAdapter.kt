package com.hmju.core.ui.binding

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory

/**
 * Description : ImageLoader BindingAdapter
 *
 * Created by juhongmin on 2022/07/23
 */
object GlideBindingAdapter {

    private val crossFadeFactory: DrawableCrossFadeFactory by lazy {
        DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
    }

    private val crossFadeTransition: DrawableTransitionOptions by lazy {
        DrawableTransitionOptions.withCrossFade(crossFadeFactory)
    }

    /**
     * 공통 이미지 로더 클래스
     */
    @JvmStatic
    @BindingAdapter(
        value = ["requestManager", "imgPath"],
        requireAll = false
    )
    fun setLoadImageUrl(
        iv: AppCompatImageView,
        requestManager: RequestManager? = null,
        url: String?
    ) {
        if (url.isNullOrEmpty()) {
            iv.visibility = View.INVISIBLE
            return
        }
        if (requestManager == null) return

        requestManager.load(url)
            .transition(crossFadeTransition)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(iv)
    }
}
