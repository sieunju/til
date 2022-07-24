package com.features.recyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.features.core_ui.BaseUiModel
import com.features.core_ui.viewholders.BaseViewHolder
import com.features.recyclerview.R
import com.features.recyclerview.binding.RefactorDiffUtilBindingAdapter
import com.features.recyclerview.ui.independent_viewholder.SimpleLike1ViewHolder
import com.features.recyclerview.ui.independent_viewholder.SimpleLike2ViewHolder
import com.features.recyclerview.ui.independent_viewholder.SimpleLike3ViewHolder
import com.features.recyclerview.ui.independent_viewholder.SimpleLike4ViewHolder

/**
 * Description :
 *
 * Created by juhongmin on 2022/07/23
 */
class RefactorDiffUtilListAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val dataList: MutableList<BaseUiModel> by lazy { mutableListOf() }

    companion object {
        class RefactorDiffUtil(
            private val oldList: List<BaseUiModel>,
            private val newList: List<BaseUiModel>,
            private val idCompareListener: RefactorDiffUtilBindingAdapter.DiffIdTheSameListener,
            private val contentsCompareListener: RefactorDiffUtilBindingAdapter.DiffContentsTheSameListener
        ) : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return oldList.size
            }

            override fun getNewListSize(): Int {
                return newList.size
            }

            override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
                return idCompareListener.callback(oldList[oldPos], newList[newPos])
            }

            override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
                return contentsCompareListener.callback(oldList[oldPos], newList[newPos])
            }
        }
    }

    /**
     * 데이터가 변경되었을때 이전 데이터들 비교하여 갱신 처리 함수
     * @param newList oldList + 새로운 데이터 리스트
     * @param idCompareListener 아이템의 간단한 정보만 비교하는 [DiffUtil.Callback.areItemsTheSame] 리스너
     * @param contentsCompareListener 아이템의 자세한 정보를 비교하는 [DiffUtil.Callback.areContentsTheSame] 리스너
     */
    fun submitList(
        newList: List<BaseUiModel>?,
        idCompareListener: RefactorDiffUtilBindingAdapter.DiffIdTheSameListener,
        contentsCompareListener: RefactorDiffUtilBindingAdapter.DiffContentsTheSameListener
    ) {
        if (newList == null) return
        val diffResult = DiffUtil.calculateDiff(
            RefactorDiffUtil(
                dataList,
                newList,
                idCompareListener,
                contentsCompareListener
            )
        )
        dataList.clear()
        dataList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            R.layout.vh_simple_like_recyclerview_1 -> SimpleLike1ViewHolder(parent)
            R.layout.vh_simple_like_recyclerview_2 -> SimpleLike2ViewHolder(parent)
            R.layout.vh_simple_like_recyclerview_3 -> SimpleLike3ViewHolder(parent)
            R.layout.vh_simple_like_recyclerview_4 -> SimpleLike4ViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid View Type $viewType")
        }
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
}
