package com.features.room_observer.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.features.room_observer.models.ActionIntent
import com.features.room_observer.models.RoomObserverParams
import com.features.room_observer.models.State
import com.features.room_observer.models.UiState
import com.features.room_observer.usecase.RoomObserverUseCase
import com.hmju.core.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
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
	private val _state: MutableLiveData<State> by lazy { MutableLiveData() }
	val uiState: LiveData<UiState> = _state
		.map { UiState.from(it) }

	init {
		useCase(params)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.filter { it !is State.Skip }
			.doOnNext {
				Timber.d("Update State ${it}")
				_state.value = it
			}
			.subscribe()
			.addTo(compositeDisposable)
	}

	fun onAction(action: ActionIntent) {
		params.sendAction(action)
	}
}