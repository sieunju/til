package com.features.compose_navigation.screens.memo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hmju.core.compose.TilComponent
import com.hmju.core.compose.TilTheme
import com.hmju.core.compose.backPressed

/**
 * Description : 메모 화면
 *
 * Created by juhongmin on 5/6/24
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemoScreen(
    navigator: NavHostController,
    viewModel: MemoViewModel = hiltViewModel()
) {
    val listState = rememberLazyListState()
    val headerTitle = viewModel.userId.collectAsState()
    val uiList = viewModel.dataList.collectAsState()

    TilComponent.HeaderAndContentsBox(
        title = "메모",
        backClick = { navigator.backPressed() }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = listState,
        ) {
            stickyHeader(
                key = "Header",
                contentType = "HeaderType"
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TilComponent.ImageLoader(
                        imageUrl = "https://til.qtzz.synology.me/resources/img/20240423/1713882085205.png",
                        modifier = Modifier
                            .size(150.dp, 150.dp)
                            .padding(30.dp)
                            .clip(RoundedCornerShape(150.dp))
                    )
                    Text(
                        text = headerTitle.value,
                        style = TilTheme.text.h2_M,
                        textAlign = TextAlign.Center
                    )
                }
            }

            itemsIndexed(
                items = uiList.value,
                key = { idx, _ -> idx },
                contentType = { _, item -> item.getType() },
                itemContent = { icx, item ->
                    item.GetUi(clickEvent = {
                        viewModel.setClickEvent(it)
                    })
                }
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.start()
    }
}