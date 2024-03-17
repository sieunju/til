package com.features.compose_ui.models.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Description :
 *
 * Created by juhongmin on 1/20/24
 */
@Serializable
data class MemoEntity(
    val id: Int = 0,
    val userId: String = "",
    val tag: Int = 0,
    val title: String = "",
    val contents: String = "",
    @SerialName("register_date")
    val registerDate: String = ""
) {
    companion object {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA)
    }

    fun getDate(): Date? {
        return try {
            format.parse(registerDate)
        } catch (ex: Exception) {
            null
        }
    }
}
