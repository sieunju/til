package com.features.recyclerview.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.features.core_ui.base.BaseUiModel
import com.features.recyclerview.adapter.RefactorDiffUtilListAdapter

/**
 * Description :
 *
 * Created by juhongmin on 2022/02/16
 */
object RefactorDiffUtilBindingAdapter {

    interface DiffIdTheSameListener {
        fun callback(oldItem: Any, newItem: Any): Boolean
    }

    interface DiffContentsTheSameListener {
        fun callback(oldItem: Any, newItem: Any): Boolean
    }

    @JvmStatic
    @BindingAdapter(value = ["diffDataList", "itemTheSame", "contentsTheSame"], requireAll = false)
    fun setDataListAndAdapter(
        rv: RecyclerView,
        dataList: List<BaseUiModel>?,
        itemTheSame: DiffIdTheSameListener?,
        contentsTheSame: DiffContentsTheSameListener?
    ) {
        if (rv.adapter == null) {
            rv.adapter = RefactorDiffUtilListAdapter()
        }

        (rv.adapter as RefactorDiffUtilListAdapter).run {
            if (itemTheSame != null && contentsTheSame != null) {
                submitList(dataList, itemTheSame, contentsTheSame)
            }
        }
    }
}