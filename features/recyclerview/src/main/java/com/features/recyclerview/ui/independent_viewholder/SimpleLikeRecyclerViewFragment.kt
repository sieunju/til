package com.features.recyclerview.ui.independent_viewholder

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.features.recyclerview.BR
import com.features.recyclerview.R
import com.features.recyclerview.databinding.FSimpleLikeRecyclerviewBinding
import com.features.recyclerview.models.entity.GoodsEntity
import com.features.recyclerview.usecase.GetGoodsUseCase
import com.hmju.core.model.params.GoodsParameter
import com.hmju.core.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

/**
 * Description : 뷰모델과 뷰홀더간의 의존성을 떼어내기 위한 학습? 공부? 고민?
 * Fragment
 *
 * Created by juhongmin on 2022/01/15
 */
@AndroidEntryPoint
class SimpleLikeRecyclerViewFragment :
    BaseFragment<FSimpleLikeRecyclerviewBinding, SimpleLikeFragmentViewModel>(R.layout.f_simple_like_recyclerview) {

    override val viewModel: SimpleLikeFragmentViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm
    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    @Inject
    lateinit var getGoodsListUseCase: GetGoodsUseCase

    private val oneTypeParams = GoodsParameter()
    private val twoTypeParams = GoodsParameter()

    private val oneAdapter = Adapter(true)
    private val twoAdapter = Adapter(false)
    private val oneList: MutableList<GoodsEntity> by lazy { mutableListOf() }
    private val twoList: MutableList<GoodsEntity> by lazy { mutableListOf() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataBindingUtil.bind<FSimpleLikeRecyclerviewBinding>(view)?.run {
            rvTop.adapter = oneAdapter
            rvBottom.adapter = twoAdapter

            button.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().apply {
                    add(R.id.fragment, SimpleLikeRecyclerViewFragment())
                    addToBackStack(null)
                    commit()
                }
            }
        }
        oneTypeParams.pageSize = 100
        getGoodsListUseCase(oneTypeParams)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                oneList.addAll(it)
                oneAdapter.submitList(oneList)
            }
            .subscribe().addTo(compositeDisposable)

        getGoodsListUseCase(twoTypeParams)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                twoList.addAll(it)
                twoAdapter.submitList(twoList)
            }
            .subscribe().addTo(compositeDisposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    class Adapter(private val isOneType: Boolean) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        companion object {
            class SimpleLikeDiffUtil(
                private val oldList: List<GoodsEntity>,
                private val newList: List<GoodsEntity>
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

        private val dataList: MutableList<GoodsEntity> by lazy { mutableListOf() }

        fun submitList(newList: List<GoodsEntity>?) {
            if (newList == null) return

            val diffResult = DiffUtil.calculateDiff(SimpleLikeDiffUtil(dataList, newList))
            dataList.clear()
            dataList.addAll(newList)
            diffResult.dispatchUpdatesTo(this)
        }

        override fun getItemViewType(pos: Int): Int {
            return when {
                pos % 4 == 1 -> {
                    R.layout.vh_simple_like_recyclerview_1
                }

                pos % 4 == 2 -> {
                    R.layout.vh_simple_like_recyclerview_2
                }

                pos % 4 == 3 -> {
                    R.layout.vh_simple_like_recyclerview_3
                }

                else -> {
                    R.layout.vh_simple_like_recyclerview_4
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (viewType) {
                R.layout.vh_simple_like_recyclerview_1 -> SimpleLike1ViewHolder(
                    parent
                )

                R.layout.vh_simple_like_recyclerview_2 -> SimpleLike2ViewHolder(
                    parent
                )

                R.layout.vh_simple_like_recyclerview_3 -> SimpleLike3ViewHolder(
                    parent
                )

                else -> SimpleLike4ViewHolder(parent)
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            runCatching {
                if (holder is BaseSimpleLikeViewHolder<*>) {
                    holder.onBindView(dataList[position])
                } else if (holder is SimpleLike3ViewHolder) {
                    holder.onBindView(dataList[position])
                } else if (holder is SimpleLike4ViewHolder) {
                    holder.onBindView(dataList[position])
                }
            }
        }

        override fun getItemCount(): Int {
            return dataList.size
        }
    }
}
