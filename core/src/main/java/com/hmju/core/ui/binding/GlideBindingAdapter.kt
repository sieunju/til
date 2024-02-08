package com.hmju.core.ui.binding

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
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

    @JvmStatic
    @BindingAdapter("imgPath")
    fun setLoadImageUrl(
        img: AppCompatImageView,
        url: String?
    ) {
        if (url.isNullOrEmpty()) {
            img.visibility = View.INVISIBLE
            return
        }

        Glide.with(img.context)
            .load(url)
            .transition(crossFadeTransition)
            .into(img)
    }
}
