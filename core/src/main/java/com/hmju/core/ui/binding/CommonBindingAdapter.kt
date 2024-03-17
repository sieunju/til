package com.hmju.core.ui.binding

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hmju.core.like_manager.LikeManager
import com.hmju.core.ui.adapter.ItemListAdapter
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.base.BaseViewModel

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

    /**
     * 공통 어댑터 Adapter 연결하는 함수
     */
    @JvmStatic
    @BindingAdapter(
        value = ["dataList", "viewModel"],
        requireAll = false
    )
    fun setItemListAdapter(
        rv: RecyclerView,
        newList: List<BaseUiModel>?,
        viewModel: BaseViewModel?
    ) {
        if (rv.adapter == null) {
            rv.adapter = ItemListAdapter()
        }

        (rv.adapter as ItemListAdapter).run {
            setViewModel(viewModel)
            submitList(newList)
        }
    }
}
