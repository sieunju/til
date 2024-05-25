package com.features.compose_navigation.screens.memo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hmju.core.compose.TilComponent
import com.hmju.core.compose.TilTheme
import com.hmju.core.compose.backPressed
import timber.log.Timber

/**
 * Description : 메모 화면
 *
 * Created by juhongmin on 5/6/24
 */
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MemoScreen(
    navigator: NavHostController,
    viewModel: MemoViewModel = hiltViewModel()
) {
    val scrollState = rememberLazyListState()
    val headerTitle = viewModel.userId.collectAsState()
    val uiList = viewModel.dataList.collectAsState()
    val maxHeaderContentHeight = 200.dp
    val minHeaderContentHeight = 60.dp
    val maxOffset = with(LocalDensity.current) { maxHeaderContentHeight.roundToPx().toFloat() }
    val minOffset = with(LocalDensity.current) { minHeaderContentHeight.roundToPx().toFloat() }
    var dynamicContentHeight = 200.dp

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    TilComponent.HeaderAndContentsBox(
        title = "메모",
        backClick = { navigator.backPressed() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(TilTheme.color.white)
                .height(maxHeaderContentHeight)
                .then(
                    Modifier.graphicsLayer {
                        val firstIndex = scrollState.firstVisibleItemIndex
                        val scrollOffset = scrollState.firstVisibleItemScrollOffset.toFloat()
                        Timber.d("ScrollOffset ${firstIndex} / ${scrollOffset}")
                        // 0 .. 390
                        if (firstIndex == 0) {
                            if (scrollOffset >= maxOffset.minus(minOffset)) {
                                translationY = -maxOffset.minus(minOffset)
                            } else {
                                translationY = -scrollOffset
                            }
                            // alpha = 1 - (scrollOffset / maxOffset).coerceIn(0.5F, 1F)
                        } else {
                            translationY = -maxOffset.minus(minOffset)
                        }
                    }
                )
        ) {
            TilComponent.ImageLoader(
                imageUrl = "https://til.qtzz.synology.me/resources/img/20240507/1715084116936.png",
                modifier = Modifier
                    .size(150.dp, 150.dp)
                    .padding(30.dp)
                    .clip(RoundedCornerShape(150.dp))
            )
            Text(
                text = headerTitle.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(TilTheme.color.blue),
                color = TilTheme.color.white,
                textAlign = TextAlign.Center
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = scrollState
        ) {
//            item(
//                key = -1,
//                contentType = { "HeaderEmpty" },
//                content = {
//                    Spacer(
//                        modifier = Modifier
//                            .height(maxHeaderContentHeight)
//                            .background(TilTheme.color.gray3)
//                    )
//                }
//            )
            itemsIndexed(
                items = uiList.value,
                key = { idx, _ -> idx },
                contentType = { _, item -> item.getType() },
                itemContent = { _, item -> item.GetUi { viewModel.setClickEvent(it) } }
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.start()
    }
}