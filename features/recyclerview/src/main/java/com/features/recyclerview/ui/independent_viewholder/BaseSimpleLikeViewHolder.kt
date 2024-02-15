package com.features.recyclerview.ui.independent_viewholder

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.features.recyclerview.entrypoint.SimpleLikeEntryPoint
import com.features.recyclerview.models.ui.GoodsModel
import com.features.recyclerview.usecase.AddLikeUseCase
import com.features.recyclerview.usecase.RemoveLikeUseCase
import com.hmju.core.like_manager.LikeManager
import com.hmju.core.models.body.LikeRequestBody
import com.hmju.core.rxbus.RxBus
import com.hmju.core.rxbus.SimpleLikeEvent
import com.hmju.core.ui.viewholders.BaseViewHolder
import dagger.hilt.EntryPoints
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Description : Base Like ViewHolder Class
 *
 * Created by juhongmin on 2022/01/15
 */
abstract class BaseSimpleLikeViewHolder<T : ViewDataBinding>(
    parent: ViewGroup,
    @LayoutRes layoutId: Int
) : BaseViewHolder<T>(parent, layoutId),
    LifecycleEventObserver {

    private val entryPoint: SimpleLikeEntryPoint by lazy {
        EntryPoints.get(
            itemView.context.applicationContext,
            SimpleLikeEntryPoint::class.java
        )
    }

    private val addLikeUseCase: AddLikeUseCase by lazy { entryPoint.addLikeUseCase() }
    private val removeLikeUseCase: RemoveLikeUseCase by lazy { entryPoint.removeLikeUseCase() }

    private var likeChangeDisposable: Disposable? = null
    private var likeRequestDisposable: Disposable? = null
    private var lifecycleOwner: LifecycleOwner? = null

    abstract fun onRefreshLike()

    init {
        itemView.doOnAttach {
            // JLogger.d("${this.javaClass.simpleName} onAttach")
            lifecycleOwner = it.findViewTreeLifecycleOwner()?.let { owner ->
                owner.lifecycle.addObserver(this)
                return@let owner
            }
        }


        itemView.doOnDetach {
            lifecycleOwner?.lifecycle?.removeObserver(this)
            lifecycleOwner = null
            closeLikeChangeDisposable()
            closeRequestDisposable()
        }
    }

    private fun initLikeChange() {
        if (likeChangeDisposable != null) {
            closeLikeChangeDisposable()
        }
        likeChangeDisposable = RxBus.listen(SimpleLikeEvent::class.java)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { onRefreshLike() }
            .subscribe()
    }

    protected fun simpleLikeClick(view: View, item: GoodsModel?) {
        if (item == null) return

        view.isSelected = !view.isSelected
        requestLike(view.isSelected, item)
    }

    protected fun simpleLikeChange(view: View, item: GoodsModel?) {
        if (item == null) return

        // 좋아요 표시를 해야 하는 아이템
        if (LikeManager.isLike(item.id)) {
            if (!view.isSelected) {
                binding.invalidateAll()
            }
        } else if (view.isSelected) {
            // 좋아요 표시를 하면 안되는 아이템이 선택이 되어있다 -> 해제.
            binding.invalidateAll()
        }
    }

    /**
     * 좋아요 추가 / 해제 요청
     * @param isAdd 추가 / 해제
     * @param item 좋아요 하는 상품 데이터
     */
    private fun requestLike(isAdd: Boolean, item: GoodsModel) {
        if (likeRequestDisposable != null) {
            closeRequestDisposable()
        }
        val reqApi =
            if (isAdd) addLikeUseCase(LikeRequestBody(item.id))
            else removeLikeUseCase(item.id)

        likeRequestDisposable = reqApi
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                Toast.makeText(
                    itemView.context,
                    if (isAdd) "좋아요" else "좋아요 취소",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .subscribe()
    }

    /**
     * Close Disposable
     */
    private fun closeLikeChangeDisposable() {
        likeChangeDisposable?.dispose()
        likeChangeDisposable = null
    }

    /**
     * Close Disposable
     */
    private fun closeRequestDisposable() {
        likeRequestDisposable?.dispose()
        likeRequestDisposable = null
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_RESUME) {
            if (itemView.isAttachedToWindow) {
                onRefreshLike()
            }

            initLikeChange()
        } else if (event == Lifecycle.Event.ON_STOP) {
            closeLikeChangeDisposable()
            closeRequestDisposable()
        }
    }
}
