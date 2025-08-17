package com.features.room_observer.models

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.hmju.core.compose.BaseListClickEvent
import com.hmju.core.compose.BaseUiModel
import com.hmju.core.compose.TilComponent.shimmerLoading
import com.hmju.core.compose.TilTheme

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 17.
 */
data class LoadingUiModel(
	val uid: Long = System.currentTimeMillis()
) : BaseUiModel {
	override fun getType(): String {
		return "LoadingUiModel"
	}

	@Composable
	override fun GetUi(clickEvent: (BaseListClickEvent) -> Unit) {
		Box(
			modifier = Modifier
				.padding(16.dp)
		) {
			Column(
				modifier = Modifier
                 .clip(RoundedCornerShape(16.dp))
                 .shadow(8.dp)
                 .background(TilTheme.color.white)
                 .padding(bottom = 10.dp)
			) {
				Box(
					modifier = Modifier
                     .fillMaxWidth()
                     .height(150.dp)
                     .shimmerLoading()
				)
				Text(
					text = "",
					style = TilTheme.text.h3_B,
					color = TilTheme.color.black,
					modifier = Modifier
                     .padding(horizontal = 6.dp, vertical = 2.dp)
                     .clip(RoundedCornerShape(6.dp))
                     .widthIn(50.dp)
                     .shimmerLoading()
				)
				Text(
					text = "",
					style = TilTheme.text.h3_B,
					color = TilTheme.color.black,
					modifier = Modifier
                     .padding(horizontal = 6.dp, vertical = 2.dp)
                     .clip(RoundedCornerShape(6.dp))
                     .widthIn(80.dp)
                     .shimmerLoading()
				)
				Text(
					text = "",
					style = TilTheme.text.h3_B,
					color = TilTheme.color.black,
					modifier = Modifier
                     .padding(horizontal = 6.dp, vertical = 2.dp)
                     .clip(RoundedCornerShape(6.dp))
                     .widthIn(200.dp)
                     .shimmerLoading()
				)
			}
		}
	}
}