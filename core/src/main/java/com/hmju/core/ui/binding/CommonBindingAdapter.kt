package com.hmju.core.ui.binding

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hmju.core.like_manager.LikeManager
import com.hmju.core.ui.adapter.ItemListAdapter
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.base.BaseViewModel
import com.hmju.core.ui.payloads.RvPayloadModel

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

    /**
     * 원하는 ViewHolder에 업데이트 처리하고 싶을때 사용하는 함수
     * 깜박거리는 이슈 대응하기 위함
     * @param payloadModel 업데이트에 필요한 데이터 모델
     */
    @JvmStatic
    @BindingAdapter(
        value = ["payloadModel"]
    )
    fun setItemListPayload(
        rv: RecyclerView,
        payloadModel: RvPayloadModel? = null
    ) {
        if (payloadModel == null) return

        val adapter = rv.adapter ?: return
        if (!payloadModel.isValidate()) return

        // 범위가 지정되어 있는 경우
        if (payloadModel.isRangeValidate()) {
            adapter.notifyItemRangeChanged(
                payloadModel.firstPos,
                payloadModel.lastPos.coerceAtMost(adapter.itemCount),
                payloadModel.list
            )
            return
        }

        // 범위가 지정되어 있지 않으면 자동으로 처리
        val layoutManager = rv.layoutManager
        var firstPos = -1
        var lastPos = -1
        if (layoutManager is GridLayoutManager) {
            firstPos = layoutManager.findFirstVisibleItemPosition()
            lastPos = layoutManager.findLastVisibleItemPosition()
        } else if (layoutManager is LinearLayoutManager) {
            firstPos = layoutManager.findFirstVisibleItemPosition()
            lastPos = layoutManager.findLastVisibleItemPosition()
        }

        if (firstPos != -1 && lastPos != -1) {
            adapter.notifyItemRangeChanged(
                firstPos,
                lastPos,
                payloadModel.list
            )
        }
    }

}
