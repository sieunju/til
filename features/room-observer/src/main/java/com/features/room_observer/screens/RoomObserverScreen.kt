package com.features.room_observer.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hmju.core.compose.TilComponent

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
    TilComponent.HeaderAndContentsColumn(
        title = "RoomObserver",
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        backClick = closeClick
    ) {

    }
}