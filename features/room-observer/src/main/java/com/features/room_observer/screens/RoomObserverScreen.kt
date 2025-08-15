package com.features.room_observer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.features.room_observer.models.AccountBusEvent
import com.features.room_observer.models.ActionIntent
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
	val state = remember { mutableStateOf(viewModel.state) }

	TilComponent.HeaderAndContentsColumn(
		title = "RoomObserver",
		modifier = Modifier
			.fillMaxSize()
			.background(Color.White),
		backClick = closeClick
	) {
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
		Spacer(modifier = Modifier.size(15.dp))
		Row(
			horizontalArrangement = Arrangement.spacedBy(10.dp)
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
}

