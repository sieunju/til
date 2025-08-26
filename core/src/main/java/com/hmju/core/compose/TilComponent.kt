package com.hmju.core.compose

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hmju.core.R
import kotlin.math.tan

/**
 * Description : Compose TIL Component
 *
 * Created by juhongmin on 4/10/24
 */
object TilComponent {

	@SuppressLint("ModifierParameter")
	@OptIn(ExperimentalComposeUiApi::class)
	@Composable
	fun EditText(
		text: MutableState<String>,
		labelText: String,
		placeHolderText: String,
		keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
		nextAction: FocusDirection = FocusDirection.Exit,
		maxLines: Int = 1,
		textStyle: TextStyle = TilTheme.text.h4,
		focusModifier: Modifier = Modifier
			.fillMaxWidth()
			.border(2.dp, TilTheme.color.black, shape = RoundedCornerShape(15.dp)),
		unFocusModifier: Modifier = Modifier
			.fillMaxWidth()
			.border(2.dp, TilTheme.color.gray3, shape = RoundedCornerShape(15.dp))
	) {
		var isFocused by remember { mutableStateOf(false) }
		val focusManager = LocalFocusManager.current
		val modifier = if (isFocused) focusModifier else unFocusModifier
		Box(
			modifier = Modifier
				.onFocusChanged { isFocused = it.isFocused }
				.then(modifier)
		) {
			TextField(
				value = text.value,
				onValueChange = { text.value = it },
				textStyle = textStyle,
				label = {
					if (isFocused || text.value.isNotEmpty()) {
						Text(
							text = labelText,
							color = TilTheme.color.black,
							style = textStyle.copy(fontSize = (textStyle.fontSize.value - 5).sp)
						)
					} else {
						Text(text = placeHolderText, style = textStyle)
					}
				},
				placeholder = {
					Text(text = placeHolderText, style = textStyle)
				},
				keyboardOptions = keyboardOptions,
				keyboardActions = KeyboardActions { focusManager.moveFocus(nextAction) },
				singleLine = maxLines <= 1,
				maxLines = maxLines,
				colors = TextFieldDefaults.colors(
					focusedContainerColor = Color.Transparent,
					disabledContainerColor = Color.Transparent,
					unfocusedContainerColor = Color.Transparent,
					errorContainerColor = Color.Transparent,
					focusedIndicatorColor = Color.Transparent,
					unfocusedIndicatorColor = Color.Transparent
				)
			)

//            if (isFocused) {
//                Box(modifier = focusBg)
//            } else {
//                Box(modifier = unFocusBg)
//            }
		}
	}

	@Composable
	fun HeaderBackButton(
		title: String,
		backClick: () -> Unit,
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.wrapContentSize()
				.background(TilTheme.color.white)
		) {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.height(50.dp),
				verticalAlignment = Alignment.CenterVertically
			) {
				Box(
					modifier = Modifier
						.width(50.dp)
						.fillMaxHeight()
						.clickable { backClick() },
					contentAlignment = Alignment.Center
				) {
					Image(
						painter = painterResource(R.drawable.ic_arrow_left),
						contentDescription = null
					)
				}
				Text(
					text = title,
					style = TilTheme.text.h4_B,
					modifier = Modifier.weight(1F),
					textAlign = TextAlign.Center
				)
				Spacer(Modifier.width(50.dp))
			}

			Spacer(
				Modifier
					.fillMaxWidth()
					.height(1.dp)
					.background(TilTheme.color.gray4)
			)
		}
	}

	@SuppressLint("ModifierParameter")
	@Composable
	fun HeaderAndContentsBox(
		title: String,
		backClick: () -> Unit,
		modifier: Modifier = Modifier
			.fillMaxSize(),
		content: @Composable BoxScope.() -> Unit
	) {
		Scaffold(
			modifier = Modifier
				.fillMaxSize(),
			topBar = { HeaderBackButton(title, backClick) }
		) { paddings ->
			Box(
				modifier = modifier
					.padding(paddings)
			) { content() }
		}
	}

	@SuppressLint("ModifierParameter")
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	fun HeaderAndContentsColumn(
		title: String,
		backClick: () -> Unit,
		modifier: Modifier = Modifier
			.fillMaxSize(),
		contentAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
		scrollState: ScrollState? = null,
		content: @Composable ColumnScope.() -> Unit
	) {

		val verticalScrollState = scrollState ?: rememberScrollState()

		Scaffold(
			modifier = Modifier
				.fillMaxSize(),
			topBar = { HeaderBackButton(title, backClick) }
		) { paddings ->
			Column(
				modifier = modifier
					.padding(paddings)
					.verticalScroll(verticalScrollState),
				horizontalAlignment = contentAlignment
			) { content() }
		}
	}

	@OptIn(ExperimentalGlideComposeApi::class)
	@SuppressLint("ModifierParameter")
	@Composable
	fun ImageLoader(
		imageUrl: String,
		contentScale: ContentScale = ContentScale.Crop,
		modifier: Modifier = Modifier,
	) {
		GlideImage(
			model = imageUrl,
			contentDescription = null,
			modifier = modifier,
			loading = placeholder(ColorPainter(TilTheme.color.gray3Light)),
			failure = placeholder(R.drawable.ic_error),
			contentScale = contentScale
		) { requestBuilder ->
			requestBuilder.diskCacheStrategy(DiskCacheStrategy.NONE)
		}
	}

	@Composable
	fun Modifier.shimmerLoading(
		durationMillis: Int = 1500,
	): Modifier = composed {
		var size by remember { mutableStateOf(IntSize.Zero) }
		val transition = rememberInfiniteTransition(label = "shimmer")

		val startOffsetX by transition.animateFloat(
			initialValue = -2 * size.width.toFloat(),
			targetValue = 2 * size.width.toFloat(),
			animationSpec = infiniteRepeatable(
				animation = tween(
					durationMillis = durationMillis,
					easing = LinearEasing,
				),
				repeatMode = RepeatMode.Restart,
			),
			label = "shimmer",
		)

		background(
			brush = Brush.linearGradient(
				colors = listOf(
					Color.LightGray.copy(alpha = 0.2f),
					Color.LightGray.copy(alpha = 1.0f),
					Color.LightGray.copy(alpha = 0.2f),
				),
				start = Offset(startOffsetX, 0f),
				end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat()),
			)
		).onGloballyPositioned {
			size = it.size
		}
	}

}
