package com.features.room_observer.models

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.hmju.core.compose.BaseListClickEvent
import com.hmju.core.compose.BaseUiModel
import com.hmju.core.compose.TilComponent
import com.hmju.core.compose.TilTheme

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
data class GoodsUiModel(
	val item: Goods
) : BaseUiModel {
	override fun getType(): String {
		return "GoodsUiModel"
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
				TilComponent.ImageLoader(
					imageUrl = item.imagePath,
					modifier = Modifier
						.fillMaxWidth()
						.height(150.dp)
				)

				Text(
					text = item.userId,
					style = TilTheme.text.h3_B,
					color = TilTheme.color.black,
					modifier = Modifier
						.padding(horizontal = 6.dp, vertical = 2.dp)
				)
				Text(
					text = item.title,
					style = TilTheme.text.h3_B,
					color = TilTheme.color.black,
					modifier = Modifier
						.padding(horizontal = 6.dp, vertical = 2.dp)
				)
				Text(
					text = item.message,
					style = TilTheme.text.h3_B,
					color = TilTheme.color.black,
					modifier = Modifier
						.padding(horizontal = 6.dp, vertical = 2.dp)
				)
			}
		}
	}
}