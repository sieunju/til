package com.features.fragment_navigation

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.features.fragment_navigation.databinding.ActivityFragmentNavigationRootBinding
import com.features.fragment_navigation.navigation.FragmentBackPressHandler
import com.features.fragment_navigation.navigation.FragmentDestination
import com.features.fragment_navigation.navigation.FragmentNavigator
import com.features.fragment_navigation.navigation.FragmentNavigatorHost
import com.features.fragment_navigation.navigation.FragmentNavigatorImpl
import com.features.fragment_navigation.navigation.LaunchMode
import com.features.fragment_navigation.ui.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description : STANDARD / SINGLE_TOP / CLEAR_TOP 3가지 launchMode 로
 * Fragment 스택을 다루는 CustomFragmentNavigation 데모
 *
 * Created by juhongmin on 2026. 7. 26.
 */
@AndroidEntryPoint
internal class RootActivity : AppCompatActivity(), FragmentNavigatorHost {

    private lateinit var binding: ActivityFragmentNavigationRootBinding

    override val fragmentNavigator: FragmentNavigator by lazy {
	FragmentNavigatorImpl(supportFragmentManager, binding.fragmentContainer.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	binding = ActivityFragmentNavigationRootBinding.inflate(layoutInflater)
	setContentView(binding.root)

	ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets ->
	    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
	    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
	    insets
	}

	binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

	onBackPressedDispatcher.addCallback(this) {
	    val consumedByFragment =
		(fragmentNavigator.currentFragment() as? FragmentBackPressHandler)?.onBackPressed() == true
	    if (consumedByFragment) return@addCallback

	    if (fragmentNavigator.back()) {
		onStackChanged()
	    } else {
		isEnabled = false
		onBackPressedDispatcher.onBackPressed()
	    }
	}

	if (savedInstanceState == null) {
	    navigate(FragmentDestination(TAG_HOME) { HomeFragment() }, LaunchMode.STANDARD)
	} else {
	    onStackChanged()
	}
    }

    override fun onStackChanged() {
	binding.tvStack.text = "Stack: ${fragmentNavigator.currentStack().joinToString(" > ")}"
    }

    companion object {
	const val TAG_HOME = "home"
	const val TAG_DETAIL = "detail"
	const val TAG_SEARCH = "search"
	const val TAG_RESULT = "result"
    }
}
