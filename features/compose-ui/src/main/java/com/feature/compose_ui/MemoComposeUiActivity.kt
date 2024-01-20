package com.feature.compose_ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.feature.compose_ui.model.MemoEntity
import com.feature.compose_ui.usecase.GetMemoListUseCase
import com.hmju.core.model.params.MemoParameter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * Description : Memo Compose Ui
 *
 * Created by juhongmin on 1/20/24
 */
@AndroidEntryPoint
class MemoComposeUiActivity : AppCompatActivity() {

    @Inject
    lateinit var getUseCase: GetMemoListUseCase
    private val params: MemoParameter by lazy { MemoParameter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemoListScreen()
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun MemoListScreen() {
        val dataList = remember { mutableStateListOf<MemoEntity>() }
        val state = rememberLazyListState()
        LaunchedEffect(Unit) {
            dataList.addAll(getUseCase(params))
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = state
        ) {
            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(Color.White)
                ) {
                    Text(
                        text = "메모 리스트 페이지",
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(top = 15.dp, bottom = 15.dp),
                        style = JTheme.h3,
                        textAlign = TextAlign.Center
                    )
                }
            }
            itemsIndexed(
                items = dataList,
                key = { _, item -> item.id },
                contentType = { _, item -> "Memo" }
            ) { _, item ->
                MemoItem(item)
            }
        }
        val position = remember { derivedStateOf { state.firstVisibleItemIndex } }
        val updatePosition = (dataList.size - position.value) / 2
        if (position.value >= updatePosition) {
            Timber.d("페이징 처리합니다. $updatePosition")
            LaunchedEffect(Unit) {
                dataList.addAll(getUseCase(params))
            }
        }
    }

    @Composable
    private fun MemoItem(item: MemoEntity) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .background(Color.Gray)
                .wrapContentHeight()
        ) {
            Text(
                text = item.title,
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 30.dp),
                color = Color.Blue,
                style = JTheme.h3
            )
            Text(
                text = item.contents,
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 30.dp),
                style = JTheme.h5
            )
            Text(
                text = item.registerDate,
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 30.dp),
                style = JTheme.h5
            )
        }
    }
}
