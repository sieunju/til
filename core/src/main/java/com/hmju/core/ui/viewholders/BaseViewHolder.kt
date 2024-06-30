package com.hmju.core.ui.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.base.BaseUiModel
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

    /**
     * 현재 보여지는 화면에서 업데이트 하고 싶을때 호출하는 함수
     * 매개변수에 있는 데이터 모델을 현재 가지고 있는 데이터 모델과 같다면 업데이트 하도록 처리
     * @param payloads 업데이트할 데이터 리스트
     */
    open fun onPayloadBindView(payloads: List<Any>) {}

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
    protected fun bindRequestManager(@IdRes variableId: Int, viewModel: BaseViewModel?) {
        if (viewModel == null) return
        if (viewModel is FragmentViewModel) {
            binding.setVariable(variableId, viewModel.requestManager)
        } else if (viewModel is ActivityViewModel) {
            binding.setVariable(variableId, viewModel.requestManager)
        }
    }

    /**
     * payloads 로 가져온 데이터 리스트에 현재 ViewHolder 에서 가지고 있는 데이터 모델과
     * 같은 모델을 찾아서 리턴하는 함수
     * 찾는 즉시 리턴하기 위해서 forEach 구문 사용 X
     * @param payloads 업데이트할 데이터리스트
     * @param block 비즈니스 로직
     */
    protected inline fun <reified T : BaseUiModel> findPayloadModel(
        payloads: List<Any>,
        block: (T) -> Boolean
    ): T? {
        for (item in payloads) {
            if (item is T && block(item)) {
                return item
            }
        }
        return null
    }

}
