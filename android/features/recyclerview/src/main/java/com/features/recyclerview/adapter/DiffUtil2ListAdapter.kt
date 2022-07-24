package com.features.recyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.features.core_ui.viewholders.BaseViewHolder
import com.features.recyclerview.R
import com.features.recyclerview.diffutil.DiffUtilV2
import com.features.recyclerview.ui.independent_viewholder.SimpleLike1ViewHolder
import com.features.recyclerview.ui.independent_viewholder.SimpleLike2ViewHolder
import com.features.core_ui.BaseUiModel

/**
 * Description : DiffUtil2 Adapter
 *
 *
 * Created by juhongmin on 2022/07/09
 */
class DiffUtil2ListAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val dataList: MutableList<BaseUiModel> by lazy { mutableListOf() }

    /**
     * DiffUtilV2 사용하는 SubmitList
     */
    fun submitList(newList: List<BaseUiModel>?) {
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
        runCatching {
            if (dataList.size > pos) {
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


    private fun getViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            R.layout.vh_simple_like_recyclerview_1 -> SimpleLike1ViewHolder(parent)
            R.layout.vh_simple_like_recyclerview_2 -> SimpleLike2ViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid View Type $viewType")
        }
    }
}