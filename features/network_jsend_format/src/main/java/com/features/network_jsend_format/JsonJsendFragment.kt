package com.features.network_jsend_format

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.features.network_jsend_format.databinding.FJsonJsendBinding
import com.features.network_jsend_format.usecase.GetJSendListUseCase
import com.features.network_jsend_format.usecase.GetJSendListWithMetaUseCase
import com.features.network_jsend_format.usecase.GetJSendUseCase
import com.features.network_jsend_format.usecase.GetJSendWithMetaUseCase
import com.hmju.core.ui.base.BaseFragment
import com.hmju.core.ui.base.FragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Description : JSON JSend 규칙에 대한 Fragment
 *
 * Created by juhongmin on 2022/01/25
 */
@AndroidEntryPoint
class JsonJsendFragment :
    BaseFragment<FJsonJsendBinding, FragmentViewModel>(R.layout.f_json_jsend) {

    override val viewModel: FragmentViewModel by initViewModel()

    override val bindingVariable: Int = BR.vm

    @Inject
    lateinit var getJSendUseCase: GetJSendUseCase

    @Inject
    lateinit var getJSendWithMetaUseCase: GetJSendWithMetaUseCase

    @Inject
    lateinit var getJSendListUseCase: GetJSendListUseCase

    @Inject
    lateinit var getJSendListWithMetaUseCase: GetJSendListWithMetaUseCase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.jsend.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                setText(getJSendUseCase())
            }
        }

        binding.jsendMeta.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                setText(getJSendWithMetaUseCase())
            }
        }

        binding.jsendList.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                setText(getJSendListUseCase())
            }
        }

        binding.jsendListMeta.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                setText(getJSendListWithMetaUseCase())
            }
        }
    }

    private fun setText(entity: Any) {
        binding.tvResponse.text = entity.toString()
    }
}
