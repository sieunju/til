package com.features.recyclerview.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.hmju.core.ui.base.BaseUiModel
import timber.log.Timber

/**
 * Description : ListAdapter 넣을 DiffUtil Class
 *
 * Created by juhongmin on 2023/01/28
 */
class CommonDiffUtilV3 : DiffUtil.ItemCallback<BaseUiModel>() {

    override fun areItemsTheSame(oldItem: BaseUiModel, newItem: BaseUiModel): Boolean {
        Timber.d("areItemsTheSame ${oldItem.layoutId}")
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
