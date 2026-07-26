package com.features.fragment_navigation.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hmju.core.ui.livedata.SingleLiveEvent

/**
 * Description : Activity 범위로 공유되는 이동 이벤트 채널.
 * Fragment 는 Activity 를 casting 하지 않고 이 ViewModel(`by activityViewModels()`)만 들고
 * `navigate()` 를 호출하면 되고, 실제 FragmentManager.commit 은 RootActivity 가
 * SingleLiveEvent 를 observe 해서 수행한다 (기존 Router 의 routeEvent 패턴과 동일).
 *
 * SingleLiveEvent 는 Observer 가 STARTED 이상일 때만 값을 흘려보내므로, 백그라운드 상태에서
 * 들어온 navigate 호출이 onSaveInstanceState 이후 바로 commit 되어 크래시 나는 상황을
 * 1차로 막아준다 (완전한 안전은 RootActivity 쪽 isStateSaved 체크가 담당).
 *
 * Created by juhongmin on 2026. 7. 27.
 */
class FragmentNavigationViewModel : ViewModel() {

    private val _navigationEvent = SingleLiveEvent<NavigationCommand>()
    val navigationEvent: LiveData<NavigationCommand> get() = _navigationEvent

    fun navigate(destination: FragmentDestination, launchMode: LaunchMode = LaunchMode.STANDARD) {
	_navigationEvent.value = NavigationCommand.Navigate(destination, launchMode)
    }
}
