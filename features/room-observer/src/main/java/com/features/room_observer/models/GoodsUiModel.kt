package com.features.room_observer.models

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
				.padding(horizontal = 16.dp, vertical = 5.dp)
		) {
			Row(
				modifier = Modifier
					.clip(RoundedCornerShape(16.dp))
					.shadow(8.dp)
					.background(TilTheme.color.white)
					.padding(bottom = 10.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Box(
					modifier = Modifier
						.size(100.dp)
				) {
					TilComponent.ImageLoader(
						imageUrl = item.imagePath,
						modifier = Modifier
							.clip(RoundedCornerShape(8.dp))
							.fillMaxSize()
					)

					Text(
						text = item.userId,
						style = TilTheme.text.h4,
						color = TilTheme.color.white,
						modifier = Modifier
							.align(Alignment.BottomEnd)
							.clip(RoundedCornerShape(6.dp))
							.background(TilTheme.color.blue)
							.padding(horizontal = 6.dp, vertical = 2.dp)
					)
				}
				Column {
					Text(
						text = item.title,
						style = TilTheme.text.h4,
						color = TilTheme.color.black,
						modifier = Modifier
							.padding(horizontal = 6.dp, vertical = 2.dp)
					)
					Text(
						text = item.message,
						style = TilTheme.text.h5,
						color = TilTheme.color.gray1,
						maxLines = 3,
						modifier = Modifier
							.padding(horizontal = 6.dp)
					)
				}
			}
		}
	}
}