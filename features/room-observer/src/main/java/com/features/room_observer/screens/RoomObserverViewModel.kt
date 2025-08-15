package com.features.room_observer.screens

import com.features.room_observer.usecase.RoomObserverUseCase
import com.hmju.core.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
@HiltViewModel
class RoomObserverViewModel @Inject constructor(
    private val useCase: RoomObserverUseCase
) : BaseViewModel() {

}