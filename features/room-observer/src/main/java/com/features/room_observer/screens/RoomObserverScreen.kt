package com.features.room_observer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.features.room_observer.models.AccountBusEvent
import com.features.room_observer.models.ActionIntent
import com.features.room_observer.models.UiState
import com.hmju.core.compose.TilComponent
import com.hmju.core.compose.TilTheme
import com.hmju.core.rxbus.RxBus

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
@Composable
fun RoomObserverScreen(
	viewModel: RoomObserverViewModel = hiltViewModel(),
	closeClick: () -> Unit
) {
	val userId = remember { mutableStateOf("") }
	val uiState = viewModel.uiState.observeAsState(UiState.Unknown)
	val isMember = viewModel.isMember.observeAsState(false)

	TilComponent.HeaderAndContentsBox(
		title = "RoomObserver",
		modifier = Modifier
			.fillMaxSize()
			.background(Color.White),
		backClick = closeClick
	) {
		Column(
			modifier = Modifier.fillMaxHeight()
		) {
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 5.dp),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.spacedBy(5.dp)
			) {
				TilComponent.EditText(
					text = userId,
					labelText = "Id",
					placeHolderText = "Input Id...",
					keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
					focusModifier = Modifier
						.weight(1f)
						.border(2.dp, TilTheme.color.black, shape = RoundedCornerShape(15.dp)),
					unFocusModifier = Modifier
						.weight(1f)
						.border(2.dp, TilTheme.color.gray3, shape = RoundedCornerShape(15.dp))
				)
				Button(
					onClick = {
						if (userId.value.isEmpty()) return@Button
						RxBus.publish(AccountBusEvent.Member(userId.value))
					}, shape = RoundedCornerShape(6.dp), enabled = !isMember.value
				) {
					Text(
						text = "로그인",
						style = TilTheme.text.h4,
						color = TilTheme.color.white
					)
				}
			}

			LazyRow(
				horizontalArrangement = Arrangement.spacedBy(10.dp),
				contentPadding = PaddingValues(horizontal = 10.dp)
			) {
				item {
					Button(onClick = {
						RxBus.publish(AccountBusEvent.User)
					}, enabled = isMember.value) {
						Text(
							text = "로그아웃",
							style = TilTheme.text.h4,
							color = TilTheme.color.white
						)
					}
				}
				items(ActionIntent.entries) { action ->
					Button(onClick = {
						viewModel.onAction(action)
					}) {
						Text(
							text = action.text,
							style = TilTheme.text.h4_B,
							color = TilTheme.color.white
						)
					}
				}
			}

			when (val state = uiState.value) {
				is UiState.Loading -> {
					LoadingScreen(state)
				}

				is UiState.Empty -> {
					EmptyScreen(state)
				}

				is UiState.AContents -> {
					AContentsScreen(state, viewModel)
				}

				is UiState.BContents -> {
					BContentsScreen(state)
				}

				else -> Unit
			}
		}
	}

	LaunchedEffect(Unit) {
		userId.value = "qtzz"
	}
}

@Composable
private fun LoadingScreen(state: UiState.Loading) {
	LazyColumn {
		items(state.uiList) {
			it.GetUi { }
		}
	}
}

@Composable
private fun EmptyScreen(state: UiState.Empty) {
	Column(
		modifier = Modifier.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		TilComponent.ImageLoader(
			imageUrl = "https://til.qtzz.synology.me/resources/img/20240507/1715084116936.png",
			modifier = Modifier
				.size(150.dp, 150.dp)
				.padding(30.dp)
				.clip(RoundedCornerShape(16.dp))
		)
		Text(
			text = "Empty View",
			style = TilTheme.text.h1_B,
			color = TilTheme.color.black
		)
	}
}

@Composable
private fun AContentsScreen(
	state: UiState.AContents,
	viewModel: RoomObserverViewModel
) {
	LazyColumn {
		items(state.list) {
			it.GetUi { }
		}
		if (state.hasMore) {
			item {
				Button(
					onClick = {
						viewModel.onAction(ActionIntent.LOAD_MORE)
					}, modifier = Modifier
						.padding(horizontal = 16.dp)
						.fillMaxWidth()
						.clip(RoundedCornerShape(8.dp))
				) {
					Text(
						text = "더보기",
						textAlign = TextAlign.Center,
						style = TilTheme.text.h4_B,
						color = TilTheme.color.white
					)
				}
			}
		} else {
			item {
				Button(
					onClick = {
						viewModel.onAction(ActionIntent.FOLD)
					}, modifier = Modifier
						.padding(horizontal = 16.dp)
						.fillMaxWidth()
						.clip(RoundedCornerShape(8.dp))
				) {
					Text(
						text = "접기",
						textAlign = TextAlign.Center,
						style = TilTheme.text.h4_B,
						color = TilTheme.color.white
					)
				}
			}
		}
	}
}

@Composable
private fun BContentsScreen(state: UiState.BContents) {
	LazyColumn {
		items(state.list) {
			it.GetUi { }
		}
	}
}
