package com.hmju.core.ui.viewholders

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
import com.hmju.core.models.body.LikeRequestBody
import com.hmju.core.rxbus.RxBus
import com.hmju.core.rxbus.SimpleLikeEvent
import com.hmju.core.ui.entrypoint.SimpleLikeEntryPoint
import com.hmju.core.usecase.AddLikeUseCase
import com.hmju.core.usecase.RemoveLikeUseCase
import dagger.hilt.EntryPoints
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Description : Base Like ViewHolder Class
 *
 * Created by juhongmin on 3/2/24
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

    abstract fun onRefreshLike()

    init {
        itemView.doOnAttach { v ->
            val lifecycleOwner = v.findViewTreeLifecycleOwner()
            lifecycleOwner?.lifecycle?.addObserver(this)
        }
        itemView.doOnDetach { v ->
            val lifecycleOwner = v.findViewTreeLifecycleOwner()
            lifecycleOwner?.lifecycle?.removeObserver(this)
            closeLikeChangeDisposable()
            closeRequestDisposable()
        }
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

    protected fun simpleLikeClick(view: View, id: Long) {
        view.isSelected = !view.isSelected
        requestLike(view.isSelected, id)
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

    /**
     * 좋아요 추가 / 해제 요청
     * @param isAdd 추가 / 해제
     * @param id 좋아요 아이디
     */
    private fun requestLike(isAdd: Boolean, id: Long) {
        if (likeRequestDisposable != null) {
            closeRequestDisposable()
        }
        val reqApi = if (isAdd) {
            addLikeUseCase(LikeRequestBody(id))
        } else {
            removeLikeUseCase(id)
        }

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
}
