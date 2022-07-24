package com.features.core_ui.binding

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Description : ImageLoader BindingAdapter
 *
 * Created by juhongmin on 2022/07/23
 */
object GlideBindingAdapter {
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
            .into(img)
    }
}
