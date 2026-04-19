package com.hmju.core.network.adapter

import com.hmju.core.models.base.JSendObj
import com.hmju.core.models.base.JSendObjWithMeta
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromString
import kotlinx.serialization.serializer
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Description : JSend flat object 포맷 변환 컨버터
 *
 * 서버의 flat 포맷 (T 필드가 루트에 직접 병합된 형태)을 JSendObj / JSendObjWithMeta 로 역직렬화하기
 * 위해 JSON 을 재가공한다.
 *
 * 서버 응답 예:
 *   { "status": true, "message": null, "id": "..." }
 *
 * 재가공 후:
 *   { "status": true, "message": null, "obj": { "id": "..." } }
 *
 * Created by juhongmin on 2026/04/19
 */
class JSendFlatConverterFactory(private val json: Json) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        val rawType = getRawType(type)
        val isWithMeta = rawType == JSendObjWithMeta::class.java
        if (rawType != JSendObj::class.java && !isWithMeta) {
            return null
        }
        @Suppress("UNCHECKED_CAST")
        val loader = json.serializersModule.serializer(type) as DeserializationStrategy<Any>
        return FlatObjectConverter(loader, json, isWithMeta)
    }

    private class FlatObjectConverter<T>(
        private val loader: DeserializationStrategy<T>,
        private val json: Json,
        private val isWithMeta: Boolean
    ) : Converter<ResponseBody, T> {

        private val reservedKeys = setOf("status", "message")

        override fun convert(value: ResponseBody): T? {
            val rootObj = json.decodeFromString<JsonObject>(value.string())
            val newMap = mutableMapOf<String, JsonElement>()

            // status, message 는 BaseJSend 필드로 그대로 유지
            rootObj["status"]?.let { newMap["status"] = it }
            rootObj["message"]?.let { newMap["message"] = it }

            // JSendObjWithMeta 인 경우 meta 도 루트 레벨에서 분리
            if (isWithMeta) {
                rootObj["meta"]?.let { newMap["meta"] = it }
            }

            // 나머지 필드 (T 의 필드들) 를 "obj" 로 래핑
            val excludeKeys = if (isWithMeta) reservedKeys + "meta" else reservedKeys
            newMap["obj"] = JsonObject(rootObj.filter { it.key !in excludeKeys })

            return json.decodeFromString(loader, JsonObject(newMap).toString())
        }
    }
}
