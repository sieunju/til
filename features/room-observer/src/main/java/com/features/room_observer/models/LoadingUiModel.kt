package com.features.room_observer.models

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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
				.fillMaxWidth()
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
						.clip(RoundedCornerShape(8.dp))
						.shimmerLoading()
				)
				Column {
					Text(
						text = "",
						style = TilTheme.text.h4,
						color = TilTheme.color.black,
						modifier = Modifier
							.padding(horizontal = 6.dp, vertical = 2.dp)
							.clip(RoundedCornerShape(6.dp))
							.widthIn(100.dp)
							.shimmerLoading()
					)
					Text(
						text = "",
						style = TilTheme.text.h5,
						color = TilTheme.color.gray1,
						modifier = Modifier
							.padding(horizontal = 6.dp)
							.clip(RoundedCornerShape(6.dp))
							.widthIn(200.dp)
							.shimmerLoading()
					)
				}
			}
		}
	}
}