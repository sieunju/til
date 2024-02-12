package com.hmju.core.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.hmju.core.ui.base.BaseUiModel

/**
 * Description : ListAdapter 넣을 DiffUtil Class
 *
 * Created by juhongmin on 2023/01/28
 */
class BaseDiffUtilV2 : DiffUtil.ItemCallback<BaseUiModel>() {

    override fun areItemsTheSame(oldItem: BaseUiModel, newItem: BaseUiModel): Boolean {
        return if (oldItem.getClassName() == newItem.getClassName()) {
            oldItem.areItemsTheSame(newItem)
        } else {
            false
        }
    }

    override fun areContentsTheSame(oldItem: BaseUiModel, newItem: BaseUiModel): Boolean {
        return if (oldItem.getClassName() == newItem.getClassName()) {
            oldItem.areContentsTheSame(newItem)
        } else {
            false
        }
    }
}
