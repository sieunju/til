package com.features.room_observer.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.toLiveData
import com.features.room_observer.models.AccountBusEvent
import com.features.room_observer.models.ActionIntent
import com.features.room_observer.models.RoomObserverParams
import com.features.room_observer.models.State
import com.features.room_observer.models.UiState
import com.features.room_observer.usecase.RoomObserverUseCase
import com.hmju.core.rxbus.RxBus
import com.hmju.core.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
@HiltViewModel
class RoomObserverViewModel @Inject constructor(
	useCase: RoomObserverUseCase
) : BaseViewModel() {

	private val params: RoomObserverParams by lazy { RoomObserverParams() }
	val uiState: LiveData<UiState> = useCase(params)
		.filter { it !is State.Skip }
		.map { UiState.from(it) }
		.toLiveData()
	val isMember: LiveData<Boolean> = RxBus.behavior(AccountBusEvent::class.java)
		.map { it is AccountBusEvent.Member }
		.startWith(Flowable.just(false))
		.distinctUntilChanged()
		.toLiveData()

	init {
//		useCase(params)
//			.filter { it !is State.Skip }
//			.compose(RxUtil.flowableUi())
//			.doOnNext { _state.value = it }
//			.doAfterNext {
//				if (it is State.LogOut) {
//					params.sendAction(ActionIntent.INIT)
//				}
//			}
//			.subscribe()
//			.addTo(compositeDisposable)

//		RxBus.listen(AccountBusEvent.User::class.java)
//			.compose(RxUtil.flowableUi())
//			.doOnNext {
//				params.sendAction(ActionIntent.INIT)
//				_state.value = State.LogOut
//			}
//			.subscribe().addTo(compositeDisposable)
	}

	fun onAction(action: ActionIntent) {
		if (action == ActionIntent.LOADED) return
		params.sendAction(action)
	}
}