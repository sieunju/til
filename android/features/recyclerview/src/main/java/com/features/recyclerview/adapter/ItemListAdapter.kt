package com.features.recyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.features.core_ui.BaseUiModel
import com.features.core_ui.viewholders.BaseViewHolder
import com.features.recyclerview.R
import com.features.recyclerview.diffutil.DiffUtilV2
import com.features.recyclerview.ui.independent_viewholder.SimpleLike1ViewHolder
import com.features.recyclerview.ui.independent_viewholder.SimpleLike2ViewHolder

/**
 * Description : TIL 공통 아이템 리스트 어댑터 클래스
 *
 * Created by juhongmin on 2022/02/16
 */
class ItemListAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val dataList: MutableList<BaseUiModel> by lazy { mutableListOf() }

    /**
     * 데이터가 변경되었을때 이전 데이터들 비교하여 갱신 처리 함수
     * @param newList oldList + 새로운 데이터 리스트
     */
    fun submitList(
        newList: List<BaseUiModel>?
    ) {
        if (newList == null) return
        val diffResult = DiffUtil.calculateDiff(DiffUtilV2(dataList, newList))
        dataList.clear()
        dataList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return getViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, pos: Int) {
        if (dataList.size > pos) {
            runCatching {
                holder.onBindView(dataList[pos])
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(pos: Int): Int {
        return if (dataList.size > pos) {
            dataList[pos].layoutId
        } else {
            super.getItemViewType(pos)
        }
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<*>) {
        super.onViewAttachedToWindow(holder)
        holder.onViewAttachedToWindow()
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<*>) {
        super.onViewDetachedFromWindow(holder)
        holder.onViewDetachedFromWindow()
    }

    private fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            R.layout.vh_simple_like_recyclerview_1 -> SimpleLike1ViewHolder(parent)
            R.layout.vh_simple_like_recyclerview_2 -> SimpleLike2ViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid View Type $viewType")
        }
    }
}