package com.features.room_observer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
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

	TilComponent.HeaderAndContentsBox(
		title = "RoomObserver",
		modifier = Modifier
			.fillMaxSize()
			.background(Color.White),
		backClick = closeClick
	) {
		LazyColumn {
			item {
				Box(
					modifier = Modifier.padding(horizontal = 16.dp)
				) {
					TilComponent.EditText(
						text = userId,
						labelText = "UserId",
						placeHolderText = "Input UserId",
						keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
					)
				}
			}
			item {
				Spacer(modifier = Modifier.size(15.dp))
			}
			item {
				Row(
					horizontalArrangement = Arrangement.spacedBy(10.dp),
					modifier = Modifier
						.padding(horizontal = 10.dp)
						.align(Alignment.Center)
				) {
					Button(onClick = {
						RxBus.publish(AccountBusEvent.User)
					}) {
						Text(
							text = "로그아웃",
							style = TilTheme.text.h4_B,
							color = TilTheme.color.white
						)
					}
					Button(onClick = {
						if (userId.value.isEmpty()) return@Button
						RxBus.publish(AccountBusEvent.Member(userId.value))
					}) {
						Text(
							text = "로그인",
							style = TilTheme.text.h4_B,
							color = TilTheme.color.white
						)
					}
				}
			}

			item {
				LazyRow(
					horizontalArrangement = Arrangement.spacedBy(10.dp),
					contentPadding = PaddingValues(horizontal = 10.dp)
				) {
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
			}
			val state = uiState.value
			if (state is UiState.Loading) {
				items(state.uiList) {
					it.GetUi { }
				}
			} else if (state is UiState.Empty) {
				item { EmptyScreen(state) }
			} else if (state is UiState.AContents) {
				items(state.list) {
					it.GetUi { }
				}
				if (state.hasMore) {
					item {
						Text(
							text = "더보기",
							modifier = Modifier.align(Alignment.Center),
							style = TilTheme.text.h4_B,
							color = TilTheme.color.black
						)
					}
				} else {
					item {
						Text(
							text = "접기",
							modifier = Modifier.align(Alignment.Center),
							style = TilTheme.text.h4_B,
							color = TilTheme.color.black
						)
					}
				}
			} else if (state is UiState.BContents) {
				items(state.list) {
					it.GetUi { }
				}
			}
		}
	}
}

@Composable
private fun EmptyScreen(state: UiState.Empty) {
	Column {
		TilComponent.ImageLoader(
			imageUrl = "https://til.qtzz.synology.me/resources/img/20240507/1715084116936.png",
			modifier = Modifier
				.size(150.dp, 150.dp)
				.padding(30.dp)
				.clip(RoundedCornerShape(150.dp))
		)
		Text(
			text = "Empty View",
			style = TilTheme.text.h1_B,
			color = TilTheme.color.black
		)
	}
}