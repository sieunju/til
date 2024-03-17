package com.features.compose_ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.features.compose_ui.models.MemoModel
import com.features.compose_ui.models.MemoUiModel
import com.features.compose_ui.usecase.GetMemoListUseCase
import com.hmju.core.compose.TilTheme
import com.hmju.core.models.params.PagingQueryParams
import com.hmju.core.ui.paging.PagingModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import timber.log.Timber
import java.util.Date
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
    private val params: PagingQueryParams by lazy { PagingQueryParams() }
    private val pagingModel: PagingModel by lazy { PagingModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemoListScreen()
        }
    }

    @OptIn(ExperimentalFoundationApi::class, FlowPreview::class)
    @Composable
    private fun MemoListScreen() {
        val uiList = remember { mutableStateListOf<MemoUiModel>() }
        val state = rememberLazyListState()
        LaunchedEffect(Unit) {
            pagingModel.initParams()
            uiList.addAll(getUiModels(getUseCase(params, lifecycleScope)))
            params.pageNo++
            pagingModel.isLoading = false
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 15.dp),
            state = state
        ) {
            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(TilTheme.color.white)
                ) {
                    Text(
                        text = "메모 리스트 페이지",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp, bottom = 15.dp),
                        style = TilTheme.text.h4,
                        textAlign = TextAlign.Center
                    )
                }
            }
            itemsIndexed(
                items = uiList,
                key = { idx, _ -> idx },
                contentType = { _, item -> item.getType() },
                itemContent = { _, item -> item.GetUi() }
            )
        }

        // DON'T
        val hasMore = remember {
            derivedStateOf {
                val lastPos = state.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                val itemCount = uiList.size
                val updatePos = (itemCount - lastPos) / 2
                return@derivedStateOf if (lastPos >= updatePos) {
                    pagingModel.hasMore()
                } else {
                    false
                }
            }
        }

        LaunchedEffect(hasMore) {
            snapshotFlow { hasMore.value }
                .debounce(200L)
                .collect {
                    if (it) {
                        Timber.d("API Call $params $pagingModel ${hasMore.value}")
                        pagingModel.isLoading = true
                        val list = getUseCase(params, lifecycleScope)
                        uiList.addAll(getUiModels(list))
                        params.pageNo++
                        pagingModel.isLast = list.isEmpty()
                        pagingModel.isLoading = false
                        Timber.d("UI 셋팅 완료합니다. $pagingModel ${hasMore.value}")
                    }
                }
        }
    }

    private fun getUiModels(list: List<MemoModel>): List<MemoUiModel> {
        val uiList = mutableListOf<MemoUiModel>()
        list.forEach { model ->
            uiList.add(MemoUiModel.Date(model))
            uiList.add(MemoUiModel.Divider1)
            uiList.add(MemoUiModel.Title(model))
            uiList.add(MemoUiModel.ImageAndInfo(model))
            uiList.add(MemoUiModel.Buttons(model))
            uiList.add(MemoUiModel.Divider10)
        }
        return uiList
    }

    @Preview(showBackground = true, backgroundColor = 0xFFFFFF)
    @Composable
    private fun PreviewExample() {
        val model = MemoModel(
            id = 0,
            tag = 3,
            title = "Example Title",
            contents = "Example Contents",
            registerDate = Date(),
            imageUrl = "https://til.qtzz.synology.me/resources/img/20210921/1632238064795dwalkkz7dea.png",
            imageName = "마테리얼 이미지"
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(15.dp)
        ) {
            MemoUiModel.Date(model).GetUi()
            MemoUiModel.Divider1.GetUi()
            MemoUiModel.Title(model.title).GetUi()
            MemoUiModel.ImageAndInfo(model).GetUi()
            MemoUiModel.Buttons(model).GetUi()
            MemoUiModel.Divider10.GetUi()
        }
    }
}
