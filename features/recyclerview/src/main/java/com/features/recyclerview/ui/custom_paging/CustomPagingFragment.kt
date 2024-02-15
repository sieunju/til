package com.features.recyclerview.ui.custom_paging

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hmju.core.ui.base.BaseFragment
import com.features.recyclerview.BR
import com.features.recyclerview.R
import com.features.recyclerview.databinding.FCustomPagingBinding
import com.features.recyclerview.models.entity.GoodsEntity
import com.features.recyclerview.models.ui.GoodsModel
import com.features.recyclerview.ui.independent_viewholder.BaseSimpleLikeViewHolder
import com.features.recyclerview.ui.independent_viewholder.SimpleLike1ViewHolder
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description :
 *
 * Created by juhongmin on 2022/01/21
 */
@AndroidEntryPoint
class CustomPagingFragment : BaseFragment<FCustomPagingBinding, CustomPagingViewModel>(
    R.layout.f_custom_paging
) {

    override val viewModel: CustomPagingViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    private val adapter = Adapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvContents.adapter = adapter
        with(viewModel) {
            dataList.observe(viewLifecycleOwner) { list ->
                adapter.submitList(list)
            }
            start()
        }
    }

    class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        companion object {
            class CustomPagingDiffUtil(
                private val oldList: List<GoodsModel>,
                private val newList: List<GoodsModel>
            ) : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return oldList.size
                }

                override fun getNewListSize(): Int {
                    return newList.size
                }

                override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
                    return oldList[oldPos].id == newList[newPos].id
                }

                override fun areContentsTheSame(
                    oldPos: Int,
                    newPos: Int
                ): Boolean {
                    return oldList[oldPos] == newList[newPos]
                }
            }
        }

        private val dataList: MutableList<GoodsModel> by lazy { mutableListOf() }

        fun submitList(newList: List<GoodsModel>?) {
            if (newList == null) return

            val diffResult = DiffUtil.calculateDiff(
                CustomPagingDiffUtil(
                    dataList,
                    newList
                )
            )
            dataList.clear()
            dataList.addAll(newList)
            diffResult.dispatchUpdatesTo(this)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return SimpleLike1ViewHolder(parent)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is BaseSimpleLikeViewHolder<*>) {
                runCatching {
                    holder.onBindView(dataList[position])
                }
            }
        }

        override fun getItemCount(): Int {
            return dataList.size
        }
    }
}
