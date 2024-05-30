package com.hmju.test

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    data class Response(
        @SerializedName("time")
        val date: Date
    )

    @Serializable
    data class ResponseV2(
        @SerialName("time")
        val time: Instant = Clock.System.now()
    ) {
        fun getDate(): Date {
            return Date(time.toEpochMilliseconds())
        }
    }

    @Test
    fun differ_gson_kotlinx() {
        assert(getGsonTime() == getKotlinxTime())
    }

    private fun getGsonTime(): Long {
        val str = """
            {
                "time": "2024-04-02T12:14:52.395Z"
            }
        """.trimIndent()
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()
        val res = gson.fromJson(str, Response::class.java)
        val sdf = SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss", Locale.getDefault())
        println("Gson ${sdf.format(res.date)}")
        return res.date.time
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun getKotlinxTime(): Long {
        val str = """
            {
                "time": "2024-04-02T12:14:52.395Z"
            }
        """.trimIndent()
        val json = Json {
            isLenient = true // Json 큰따옴표 느슨하게 체크.
            ignoreUnknownKeys = true // Field 값이 없는 경우 무시
            coerceInputValues = true // "null" 이 들어간경우 default Argument 값으로 대체
        }
        val res = json.decodeFromString<ResponseV2>(str)
        val sdf = SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss", Locale.getDefault())
        println("Kotlinx ${sdf.format(res.getDate())}")
        return res.getDate().time
    }

    @Test
    fun calculateTest(){
        // var offset = Math.min(1F,1- )
    }
}