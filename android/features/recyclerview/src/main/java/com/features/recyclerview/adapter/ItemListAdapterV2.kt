package com.features.recyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.features.recyclerview.diffutil.CommonDiffUtilV3
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.base.BaseViewModel
import com.hmju.core.ui.viewholders.BaseViewHolder
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

/**
 * Description : 공통 어댑터 클래스 V2 버전
 *
 * Created by juhongmin on 2023/01/28
 */
class ItemListAdapterV2 : ListAdapter<BaseUiModel, BaseViewHolder<*>>(CommonDiffUtilV3()) {

    companion object {
        private val viewHolderTypeMap: ConcurrentMap<Int, KClass<out BaseViewHolder<*>>> =
            ConcurrentHashMap()
    }

    private var viewModel: BaseViewModel? = null

    override fun submitList(list: MutableList<BaseUiModel>?) {
        handleViewTypeMap(list)
        super.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return getViewHolderType(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        runCatching {
            holder.onBindView(getItem(position))
        }
    }

    override fun getItemViewType(pos: Int): Int {
        return if (currentList.size > pos) {
            getItem(pos).layoutId
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

    /**
     * ViewHolderType 을 Map 에 저장처리하는 함수
     */
    private fun handleViewTypeMap(newList: List<BaseUiModel>?) {
        if (newList == null) return
        newList.forEach { model ->
            if (!viewHolderTypeMap.contains(model.layoutId)) {
                viewHolderTypeMap[model.layoutId] = model.getViewHolderType()
            }
        }
    }
}