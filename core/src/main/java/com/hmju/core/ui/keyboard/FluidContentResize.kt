package com.hmju.core.ui.keyboard


import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Service
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import timber.log.Timber

/**
 * Description : 키보드 자연스럽게 올라오기 위한 클래스
 *
 * Created by juhongmin on 2025. 8. 26.
 */
object FluidContentResize {

	private var heightAnimator: ValueAnimator = ObjectAnimator()
	private lateinit var imm: InputMethodManager

	/**
	 * @param activity TargetActivity
	 * @param callback 키보드가 올라옴 / 내려감 콜백 처리함수
	 */
	fun listen(activity: FragmentActivity, callback: (Boolean) -> Unit = {}) {
		val viewHolder = ActivityViewHolder.createFrom(activity)
		imm = activity.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager

		KeyboardVisibilityDetector.listen(viewHolder) {
			callback(it.visible)
			Timber.d("KeyboardChange ${it.visible}")
			animateHeight(viewHolder, it)
		}
		// 화면 꺼질때.
		viewHolder.onDetach {
			heightAnimator.cancel()
			heightAnimator.removeAllUpdateListeners()
		}
	}

	private fun animateHeight(viewHolder: ActivityViewHolder, event: KeyboardVisibilityChanged) {
		val contentView = viewHolder.contentView
		contentView.setHeight(event.contentHeightBeforeResize)

		heightAnimator.cancel()
		heightAnimator.removeAllUpdateListeners()

		// 키보드 내려갈때 콘텐츠 뷰 Resize 애니메이션 처리
		heightAnimator = ObjectAnimator.ofInt(
			event.contentHeightBeforeResize,
			event.contentHeight
		).apply {
			interpolator = FastOutSlowInInterpolator()
			duration = 300
			addUpdateListener {
				contentView.setHeight(it.animatedValue as Int)
			}
			start()
		}
	}

	private fun View.setHeight(height: Int) {
		val params = layoutParams
		params.height = height
		layoutParams = params
	}
}
