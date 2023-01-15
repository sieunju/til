package com.features.recyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.features.recyclerview.R
import com.features.recyclerview.diffutil.DiffUtilV2
import com.features.recyclerview.ui.independent_viewholder.SimpleLike1ViewHolder
import com.features.recyclerview.ui.independent_viewholder.SimpleLike2ViewHolder
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.base.BaseViewModel
import com.hmju.core.ui.viewholders.BaseViewHolder
import timber.log.Timber
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

/**
 * Description : TIL 공통 아이템 리스트 어댑터 클래스
 *
 * Created by juhongmin on 2022/02/16
 */
class ItemListAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val dataList: MutableList<BaseUiModel> by lazy { mutableListOf() }

    companion object {
        private val viewHolderTypeMap: ConcurrentMap<Int, KClass<out BaseViewHolder<*>>> =
            ConcurrentHashMap()
    }

    private var viewModel: BaseViewModel? = null
    private var targetView: ViewGroup? = null

    /**
     * 데이터가 변경되었을때 이전 데이터들 비교하여 갱신 처리 함수
     * @param newList oldList + 새로운 데이터 리스트
     */
    fun submitList(
        newList: List<BaseUiModel>?
    ) {
        if (newList == null) return
        val diffResult = DiffUtil.calculateDiff(DiffUtilV2(dataList, newList))
        handleViewTypeMap(newList)
        dataList.clear()
        dataList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    private fun handleViewTypeMap(newList: List<BaseUiModel>) {
        newList.forEach { model ->
            if (!viewHolderTypeMap.contains(model.layoutId)) {
                viewHolderTypeMap[model.layoutId] = model.getViewHolderType()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return try {
            getViewHolderType(parent, viewType)
        } catch (ex: IllegalArgumentException) {
            Timber.d("여길 타나요?? $ex")
            getLegacyViewHolder(parent, viewType)
        }
    }

    /**
     * Map 에 저장된 ViewHolderClass 를 리턴하는 함수
     *
     * @param parent ViewGroup
     * @param viewType getItemViewType
     * @return BaseViewHolderV2
     * @exception NullPointerException viewHolderTypeMap 에 없는 타입인 경우
     * @exception IllegalArgumentException 해당 클래스의 생성자를 못찾았을때
     */
    private fun getViewHolderType(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val viewHolderType = viewHolderTypeMap[viewType]
            ?: throw NullPointerException("ViewHolderMap Not Found")
        val findConstructor = viewHolderType.primaryConstructor
            ?: throw IllegalArgumentException("Not Found PrimaryConstructor")
        return if (findConstructor.parameters.size == 1) {
            // Parent Type
            findConstructor.call(parent)
        } else {
            // Parent, ViewModel Type
            findConstructor.call(parent, viewModel)
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

    @Deprecated("Legacy getViewHolder")
    private fun getLegacyViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            R.layout.vh_simple_like_recyclerview_1 -> SimpleLike1ViewHolder(parent)
            R.layout.vh_simple_like_recyclerview_2 -> SimpleLike2ViewHolder(parent)
            else -> throw IllegalArgumentException("Invalid View Type $viewType")
        }
    }
}