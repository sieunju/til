package com.features.rv_refactor_diff_util.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.features.rv_refactor_diff_util.R
import com.features.rv_refactor_diff_util.models.ui.GoodsOneModel
import com.features.rv_refactor_diff_util.models.ui.GoodsTwoModel
import com.features.rv_refactor_diff_util.ui.binding_adapter.RefactorDiffUtilBindingAdapter
import com.features.rv_refactor_diff_util.ui.viewholders.ChildRvRefactorDiffUtil1ViewHolder
import com.features.rv_refactor_diff_util.ui.viewholders.ChildRvRefactorDiffUtil2ViewHolder
import com.hmju.core.ui.base.BaseViewModel
import com.hmju.core.ui.viewholders.BaseViewHolder

/**
 * Description : Refactor DiffUtil Adapter
 *
 * Created by juhongmin on 3/8/24
 */
internal class RefactorDiffUtilListAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    companion object {
        class RefactorDiffUtil(
            private val oldList: List<Any>,
            private val newList: List<Any>,
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

    private val dataList: MutableList<Any> by lazy { mutableListOf() }
    private var viewModel: BaseViewModel? = null

    /**
     * Set ViewModel
     * @param viewModel ViewModel
     */
    fun setViewModel(viewModel: BaseViewModel?) {
        this.viewModel = viewModel
    }

    /**
     * 데이터가 변경되었을때 이전 데이터들 비교하여 갱신 처리 함수
     * @param newList oldList + 새로운 데이터 리스트
     * @param idCompareListener 아이템의 간단한 정보만 비교하는 [DiffUtil.Callback.areItemsTheSame] 리스너
     * @param contentsCompareListener 아이템의 자세한 정보를 비교하는 [DiffUtil.Callback.areContentsTheSame] 리스너
     */
    fun submitList(
        newList: List<Any>?,
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
            R.layout.vh_rv_refactor_diff_util_1 -> ChildRvRefactorDiffUtil1ViewHolder(
                parent,
                viewModel
            )

            R.layout.vh_rv_refactor_diff_util_2 -> ChildRvRefactorDiffUtil2ViewHolder(
                parent,
                viewModel
            )

            else -> throw IllegalArgumentException("Invalid ViewType")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        dataList.getOrNull(position)?.let { holder.onBindView(it) }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(pos: Int): Int {
        return when (dataList.getOrNull(pos)) {
            is GoodsOneModel -> R.layout.vh_rv_refactor_diff_util_1
            is GoodsTwoModel -> R.layout.vh_rv_refactor_diff_util_2
            else -> super.getItemViewType(pos)
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