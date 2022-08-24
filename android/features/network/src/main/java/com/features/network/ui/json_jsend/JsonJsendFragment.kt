package com.features.network.ui.json_jsend

import android.os.Bundle
import android.view.View
import com.features.core_ui.base.BaseFragment
import com.features.network.BR
import com.features.network.R
import com.features.network.databinding.FJsonJsendBinding
import com.features.network.usecase.*
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

/**
 * Description : JSON JSend 규칙에 대한 Fragment
 *
 * Created by juhongmin on 2022/01/25
 */
@AndroidEntryPoint
class JsonJsendFragment :
    BaseFragment<FJsonJsendBinding, JsonJsendFragmentViewModel>(R.layout.f_json_jsend) {

    override val viewModel: JsonJsendFragmentViewModel by initViewModel()

    override val bindingVariable: Int = BR.vm

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    @Inject
    lateinit var getJSendUseCase: GetJSendUseCase

    @Inject
    lateinit var getJSendWithMetaUseCase: GetJSendWithMetaUseCase

    @Inject
    lateinit var getJSendListUseCase: GetJSendListUseCase

    @Inject
    lateinit var getJSendListWithMetaUseCase: GetJSendListWithMetaUseCase

    @Inject
    lateinit var getErrorTestUseCase: GetErrorTestUseCase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.jsend.setOnClickListener {
            getJSendUseCase()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setText(it)
                }, {
                    setText(it)
                }).addTo(compositeDisposable)
        }

        binding.jsendMeta.setOnClickListener {
            getJSendWithMetaUseCase()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setText(it)
                }, {
                    setText(it)
                }).addTo(compositeDisposable)
        }

        binding.jsendList.setOnClickListener {
            getJSendListUseCase()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setText(it)
                }, {
                    setText(it)
                }).addTo(compositeDisposable)
        }

        binding.jsendListMeta.setOnClickListener {
            getJSendListWithMetaUseCase()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setText(it)
                }, {
                    setText(it)
                }).addTo(compositeDisposable)
        }

        binding.errorTest.setOnClickListener {
            getErrorTestUseCase()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setText(it)
                }, {
                    setText(it)
                }).addTo(compositeDisposable)
        }
    }

    private fun setText(entity: Any) {
        binding.tvResponse.text = entity.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
