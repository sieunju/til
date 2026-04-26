package com.features.activity_result

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.features.activity_result.databinding.AActivityResultRootBinding
import com.hmju.core.ui.base.BaseActivity
import com.hmju.core_navigator.Navigator
import com.hmju.core_navigator.Route
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

/**
 * Description : 
 *
 * Created by juhongmin on 2026. 4. 26.
 */
@AndroidEntryPoint
internal class RootActivity : BaseActivity<AActivityResultRootBinding, ActivityResultRootViewModel>(
    R.layout.a_activity_result_root
) {
    override val viewModel: ActivityResultRootViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    private val activityResultCallback = registerForActivityResult(
	ActivityResultContracts.StartActivityForResult()
    ) {
	val text = it.data?.getStringExtra(Constants.KEY_EDIT) ?: return@registerForActivityResult
	Timber.d("Result ${it.resultCode} $text")
	viewModel.resultText.value = "Result: $text"
    }

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	binding.toolbar.setNavigationOnClickListener { finish() }
	binding.btnStart.setOnClickListener {
	    navigator.navigateForResult(
		this,
		Route.ACTIVITY_RESULT_CALLBACK.getUri(),
		activityResultCallback
	    )
	}
    }
}
