package com.hmju.core.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.hmju.core.ui.base.BaseUiModel

/**
 * Description : DiffUtilClass
 *
 * Created by juhongmin on 2022/06/26
 */
class BaseDiffUtil(
    private val oldList: List<BaseUiModel>,
    private val newList: List<BaseUiModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        val oldItem = oldList[oldPos]
        val newItem = newList[newPos]
        return if (oldItem.getClassName() == newItem.getClassName()) {
            oldItem.areItemsTheSame(newItem)
        } else {
            false
        }
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        val oldItem = oldList[oldPos]
        val newItem = newList[newPos]
        return if (oldItem.getClassName() == newItem.getClassName()) {
            oldItem.areContentsTheSame(newItem)
        } else {
            false
        }
    }
}
