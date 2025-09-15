package com.features.rv_refactor_diff_util.ui.binding_adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.features.rv_refactor_diff_util.ui.adapter.RefactorDiffUtilListAdapter
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.base.BaseViewModel

/**
 * Description :
 *
 * Created by juhongmin on 3/8/24
 */
internal object RefactorDiffUtilBindingAdapter {

    interface DiffIdTheSameListener {
        fun callback(oldItem: Any, newItem: Any): Boolean
    }

    interface DiffContentsTheSameListener {
        fun callback(oldItem: Any, newItem: Any): Boolean
    }

    @JvmStatic
    @BindingAdapter(
        value = [
            "diffDataList",
            "viewModel",
            "itemTheSame",
            "contentsTheSame"
        ], requireAll = false
    )
    fun setDataListAndAdapter(
        rv: RecyclerView,
        dataList: List<Any>?,
        viewModel: BaseViewModel? = null,
        itemTheSame: DiffIdTheSameListener? = null,
        contentsTheSame: DiffContentsTheSameListener? = null
    ) {
        if (itemTheSame == null || contentsTheSame == null) return

        if (rv.adapter == null) {
            rv.adapter = RefactorDiffUtilListAdapter()
        }

        (rv.adapter as RefactorDiffUtilListAdapter).run {
            setViewModel(viewModel)
            submitList(dataList, itemTheSame, contentsTheSame)
        }
    }
}
