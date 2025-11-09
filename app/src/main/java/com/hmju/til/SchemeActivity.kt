package com.hmju.til

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.features.main.MainActivity
import com.hmju.core_navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Description : Scheme Activity
 *
 * Created by juhongmin on 2025. 11. 9.
 */
@AndroidEntryPoint
class SchemeActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	intent?.data?.let { handleDeeplink(it) }
	onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
	    override fun handleOnBackPressed() {
		finish()
		overridePendingTransition(0, 0)
	    }
	})
	finish()
    }

    private fun handleDeeplink(uri: Uri) {
	if (isTaskRoot) {
	    Intent(this, MainActivity::class.java).apply {
		flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
		data = uri
		startActivity(this)
	    }
	} else {
	    navigator.navigate(uri)
	}
    }
}