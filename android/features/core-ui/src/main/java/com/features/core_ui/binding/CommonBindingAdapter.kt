package com.features.core_ui.binding

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.hmju.shared.like_manager.LikeManager

/**
 * Description :
 *
 * Created by juhongmin on 2022/07/23
 */
object CommonBindingAdapter {
    @JvmStatic
    @BindingAdapter("isLikeId")
    fun setIsLikeGoods(
        view: View,
        id: Long?
    ) {
        if (id == null) return

        view.isSelected = LikeManager.isLike(id)
    }

    @JvmStatic
    @BindingAdapter("htmlText")
    fun setHtmlTextView(
        tv: AppCompatTextView,
        text: String?
    ) {
        if (text.isNullOrEmpty()) return

        tv.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}
