package com.features.room_observer.models

import com.hmju.core.rxbus.RxBusEvent

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
sealed class AccountBusEvent : RxBusEvent() {

	data object User : AccountBusEvent()

	data class Member(
		val id: String
	) : AccountBusEvent()
}
