package com.features.compose_navigation.screens.memo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
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
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MemoScreen(
    navigator: NavHostController,
    viewModel: MemoViewModel = hiltViewModel()
) {
    val scrollState = rememberLazyListState()
    val headerTitle = viewModel.userId.collectAsState()
    val uiList = viewModel.dataList.collectAsState()
    val appbarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "메모")
//                    Column {
//                        TilComponent.HeaderBackButton(
//                            title = "메모",
//                            backClick = { navigator.backPressed() }
//                        )
//                        TilComponent.ImageLoader(
//                            imageUrl = "https://til.qtzz.synology.me/resources/img/20240507/1715084116936.png",
//                            modifier = Modifier
//                                .size(150.dp, 150.dp)
//                                .padding(30.dp)
//                                .clip(RoundedCornerShape(150.dp))
//                        )
//                        Text(
//                            text = headerTitle.value,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(60.dp)
//                                .background(TilTheme.color.blue),
//                            color = TilTheme.color.white,
//                            textAlign = TextAlign.Center
//                        )
//                    }
                },
                scrollBehavior = appbarScrollBehavior
            )
        },
        // contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {
            itemsIndexed(
                items = uiList.value,
                key = { idx, _ -> idx },
                contentType = { _, item -> item.getType() },
                itemContent = { _, item -> item.GetUi { viewModel.setClickEvent(it) } }
            )
        }
    }

//    TilComponent.HeaderAndContentsBox(
//        title = "메모",
//        backClick = { navigator.backPressed() }
//    ) {
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize(),
//            state = scrollState,
//            contentPadding = PaddingValues(top = maxHeaderContentHeight)
//        ) {
////            item(
////                key = -1,
////                contentType = { "HeaderEmpty" },
////                content = {
////                    Spacer(
////                        modifier = Modifier
////                            .height(maxHeaderContentHeight)
////                            .background(TilTheme.color.gray3)
////                    )
////                }
////            )
//            itemsIndexed(
//                items = uiList.value,
//                key = { idx, _ -> idx },
//                contentType = { _, item -> item.getType() },
//                itemContent = { _, item -> item.GetUi { viewModel.setClickEvent(it) } }
//            )
//        }
//
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(TilTheme.color.white)
//                .height(maxHeaderContentHeight)
//                .offset { IntOffset(x = 0, y = -toolbarOffsetY.toInt()) }
////                .then(
////                    Modifier.graphicsLayer {
////                        val firstIndex = scrollState.firstVisibleItemIndex
////                        val scrollOffset = scrollState.firstVisibleItemScrollOffset.toFloat()
////                        Timber.d("ScrollOffset ${firstIndex} / ${scrollOffset}")
////                        // 0 .. 390
////                        if (firstIndex == 0) {
////                            if (scrollOffset >= maxOffset.minus(minOffset)) {
////                                translationY = -maxOffset.minus(minOffset)
////                            } else {
////                                translationY = -scrollOffset
////                            }
////                            // alpha = 1 - (scrollOffset / maxOffset).coerceIn(0.5F, 1F)
////                        } else {
////                            translationY = -maxOffset.minus(minOffset)
////                        }
////                    }
////                )
//        ) {
//            TilComponent.ImageLoader(
//                imageUrl = "https://til.qtzz.synology.me/resources/img/20240507/1715084116936.png",
//                modifier = Modifier
//                    .size(150.dp, 150.dp)
//                    .padding(30.dp)
//                    .clip(RoundedCornerShape(150.dp))
//            )
//            Text(
//                text = headerTitle.value,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(60.dp)
//                    .background(TilTheme.color.blue),
//                color = TilTheme.color.white,
//                textAlign = TextAlign.Center
//            )
//        }
//    }

    LaunchedEffect(Unit) {
        viewModel.start()
    }
}