package com.hmju.core.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.base.BaseViewModel
import com.hmju.core.ui.diffutil.BaseDiffUtilV2
import com.hmju.core.ui.viewholders.BaseViewHolder
import timber.log.Timber
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

/**
 * Description : 공통 어댑터 클래스 V2 버전
 *
 * Created by juhongmin on 2023/01/28
 */
class ItemListAdapterV2 : ListAdapter<BaseUiModel, BaseViewHolder<*>>(BaseDiffUtilV2()) {

    private val viewHolderTypeMap: MutableMap<Int, KClass<out BaseViewHolder<*>>> = mutableMapOf()
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
     * @param list oldList + 새로운 데이터 리스트
     */
    override fun submitList(list: List<BaseUiModel>?) {
        val newList = list?.toMutableList()
        handleViewTypeMap(newList)
        super.submitList(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return getViewHolderType(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        runCatching {
            holder.onBindView(getItem(position))
        }
    }

    /**
     * PayLoad 방식의 BindViewHolder
     */
    override fun onBindViewHolder(
        holder: BaseViewHolder<*>,
        pos: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            this.onBindViewHolder(holder, pos)
        } else if (payloads[0] is List<*>) {
            @Suppress("UNCHECKED_CAST")
            holder.onPayloadBindView(payloads[0] as List<Any>)
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
        Timber.d("getViewHolderType $viewHolderType")
        val findConstructor = viewHolderType.primaryConstructor
            ?: throw IllegalArgumentException("Not Found PrimaryConstructor")
        return if (findConstructor.parameters.size == 1) {
            Timber.d("getViewHolderType $viewHolderType")
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