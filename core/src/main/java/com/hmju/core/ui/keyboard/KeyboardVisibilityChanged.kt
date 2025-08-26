package com.hmju.core.ui.keyboard

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 26.
 */
data class KeyboardVisibilityChanged(
	val visible: Boolean,
	val contentHeight: Int,
	val contentHeightBeforeResize: Int
)
