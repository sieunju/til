package com.hmju.core.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.base.BaseViewModel
import com.hmju.core.ui.base.FragmentViewModel

/**
 * Description : TIL 공통 ViewHolder Class
 *
 * Created by juhongmin on 2022/02/16
 */
abstract class BaseViewHolder<T : ViewDataBinding>(
    parent: ViewGroup,
    @LayoutRes layoutId: Int
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)) {

    val binding: T by lazy { DataBindingUtil.bind(itemView)!! }

    @Throws(Exception::class)
    abstract fun onBindView(item: Any)

    open fun onViewAttachedToWindow() {

    }

    open fun onViewDetachedFromWindow() {

    }

    /**
     * set Lifecycle 필요시에만 함수 호출 할것
     */
    protected fun setLifecycleOwner() {
        itemView.doOnAttach {
            try {
                binding.lifecycleOwner = it.findViewTreeLifecycleOwner()
            } catch (ex: Exception) {
                // data binding failed to call observer method 변경된 BindingAdapter 나 xml 에서 databinding
            }
        }
        itemView.doOnDetach {
            binding.lifecycleOwner = null
        }
    }

    /**
     * ViewModel 에서 RequestManager 가져와서 처리하는 함수
     * @param variableId BR
     * @param viewModel ViewModel
     */
    protected fun bindRequestManager(variableId: Int, viewModel: BaseViewModel?) {
        if (viewModel == null) return
        if (viewModel is FragmentViewModel) {
            binding.setVariable(variableId, viewModel.requestManager)
        } else if (viewModel is ActivityViewModel) {
            binding.setVariable(variableId, viewModel.requestManager)
        }
    }
}
