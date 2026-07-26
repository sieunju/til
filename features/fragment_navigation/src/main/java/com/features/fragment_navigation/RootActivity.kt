package com.features.fragment_navigation

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.features.fragment_navigation.databinding.ActivityFragmentNavigationRootBinding
import com.features.fragment_navigation.navigation.FragmentBackPressHandler
import com.features.fragment_navigation.navigation.FragmentDestination
import com.features.fragment_navigation.navigation.FragmentNavigationViewModel
import com.features.fragment_navigation.navigation.FragmentNavigator
import com.features.fragment_navigation.navigation.FragmentNavigatorImpl
import com.features.fragment_navigation.navigation.LaunchMode
import com.features.fragment_navigation.navigation.NavigationCommand
import com.features.fragment_navigation.ui.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description : STANDARD / SINGLE_TOP / CLEAR_TOP 3가지 launchMode 로
 * Fragment 스택을 다루는 CustomFragmentNavigation 데모
 *
 * Fragment 들은 이 Activity 를 직접 casting 하지 않고 [FragmentNavigationViewModel] 로 이동 이벤트만
 * 보낸다. 여기서 실제 FragmentManager.commit 을 수행하는데, `isStateSaved` 인 상태(=Activity 가 이미
 * onSaveInstanceState 를 거친 상태)에서 이벤트가 도착하면 바로 commit 하지 않고 큐에 담아뒀다가
 * onResume 시점에 흘려보내서 "Can not perform this action after onSaveInstanceState" 크래시를 막는다.
 *
 * Created by juhongmin on 2026. 7. 27.
 */
@AndroidEntryPoint
internal class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentNavigationRootBinding

    private val navigationViewModel: FragmentNavigationViewModel by viewModels()

    private val fragmentNavigator: FragmentNavigator by lazy {
	FragmentNavigatorImpl(supportFragmentManager, binding.fragmentContainer.id)
    }

    private var pendingCommand: NavigationCommand? = null

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
		refreshStackLabel()
	    } else {
		isEnabled = false
		onBackPressedDispatcher.onBackPressed()
	    }
	}

	navigationViewModel.navigationEvent.observe(this) { command -> executeOrQueue(command) }

	if (savedInstanceState == null) {
	    navigationViewModel.navigate(FragmentDestination(TAG_HOME) { HomeFragment() }, LaunchMode.STANDARD)
	} else {
	    refreshStackLabel()
	}
    }

    override fun onResume() {
	super.onResume()
	pendingCommand?.let {
	    pendingCommand = null
	    applyCommand(it)
	}
    }

    private fun executeOrQueue(command: NavigationCommand) {
	if (supportFragmentManager.isStateSaved) {
	    pendingCommand = command
	} else {
	    applyCommand(command)
	}
    }

    private fun applyCommand(command: NavigationCommand) {
	when (command) {
	    is NavigationCommand.Navigate -> fragmentNavigator.navigate(command.destination, command.launchMode)
	}
	refreshStackLabel()
    }

    private fun refreshStackLabel() {
	binding.tvStack.text = "Stack: ${fragmentNavigator.currentStack().joinToString(" > ")}"
    }

    companion object {
	const val TAG_HOME = "home"
	const val TAG_DETAIL = "detail"
	const val TAG_SEARCH = "search"
	const val TAG_RESULT = "result"
    }
}
