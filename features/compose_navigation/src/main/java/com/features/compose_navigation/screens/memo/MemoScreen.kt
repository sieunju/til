package com.features.compose_navigation.screens.memo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hmju.core.compose.TilComponent
import com.hmju.core.compose.TilTheme
import com.hmju.core.compose.backPressed
import timber.log.Timber
import kotlin.math.roundToInt

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
    val density = LocalDensity.current

    // 헤더에서 스크롤
    var appBarHeight = remember { 0.dp } // 전체 헤더 높이값
    val collapseHeight = 60.dp // 접혔을때 높이값
    var expandHeightPx = 0F
    var appbarOffsetHeightPx by remember { mutableFloatStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {

            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                appbarOffsetHeightPx += available.y
                // Timber.d("onPreScroll ${available.y} $appbarOffsetHeightPx")
                return Offset.Zero
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                appbarOffsetHeightPx -= available.y
                return Offset.Zero
            }
        }
    }

    TilComponent.HeaderAndContentsBox(
        title = "메모",
        backClick = { navigator.backPressed() },
        modifier = Modifier
            .fillMaxWidth()
            .nestedScroll(nestedScrollConnection)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = scrollState,
            contentPadding = PaddingValues(top = appBarHeight.plus(30.dp))
        ) {
            itemsIndexed(
                items = uiList.value,
                key = { idx, _ -> idx },
                contentType = { _, item -> item.getType() },
                itemContent = { _, item -> item.GetUi { viewModel.setClickEvent(it) } }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    if (appBarHeight == 0.dp) {
                        appBarHeight = with(density) { coordinates.size.height.toDp() }
                        expandHeightPx = with(density) {
                            appBarHeight
                                .minus(collapseHeight)
                                .roundToPx()
                                .toFloat()
                        }
                    }
                }
                .offset {
                    IntOffset(
                        x = 0,
                        y = appbarOffsetHeightPx
                            .coerceIn(-expandHeightPx, 0f)
                            .roundToInt()
                    )
                }
                .background(TilTheme.color.defBgColor)
        ) {
            TilComponent.ImageLoader(
                imageUrl = "https://til.qtzz.synology.me/resources/img/20240507/1715084116936.png",
                modifier = Modifier
                    .padding(30.dp)
                    .size(150.dp, 150.dp)
                    .clip(RoundedCornerShape(150.dp))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(collapseHeight)
                    .background(TilTheme.color.defBgColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = headerTitle.value,
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = TilTheme.color.black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.start()
    }
}