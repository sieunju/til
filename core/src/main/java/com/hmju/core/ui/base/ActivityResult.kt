package com.hmju.core.ui.base

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.AnimRes
import androidx.fragment.app.FragmentActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.io.Serializable
import kotlin.reflect.KClass

/**
 * Description : ViewModel 에서 Activity 이동 하기위한 데이터 모델
 *
 * Created by juhongmin on 2022/04/19
 */
@Suppress("MemberVisibilityCanBePrivate")
class ActivityResult private constructor(
    val requestCode: Int = -1,
    val targetActivity: KClass<out FragmentActivity>,
    val flags: Int = -1,
    val data: Bundle = Bundle(),
    @AnimRes val enterAni: Int = -1,
    @AnimRes val exitAni: Int = -1
) {

    fun isValidateAni() : Boolean {
        return enterAni != -1 && exitAni != -1
    }
    @Suppress("unused")
    class Builder(
        val targetActivity: KClass<out FragmentActivity>
    ) {
        var requestCode: Int = -1
            private set
        var flags: Int = -1
            private set

        val data: Bundle by lazy { Bundle() }

        @AnimRes
        var enterAni: Int = -1
            private set

        @AnimRes
        var exitAni: Int = -1
            private set

        /**
         * Setter Request Code
         * @param value Code
         */
        fun setRequestCode(value: Int): Builder {
            requestCode = value
            return this
        }

        /**
         * Setter Intent Flags
         * @param value Intent.Flags
         */
        fun setFlags(value: Int): Builder {
            flags = value
            return this
        }

        /**
         * Setter Animation
         * @param enterAni 진입 애니메이션
         * @param exitAni 나갈때 애니메이션
         */
        fun setAni(
            @AnimRes enterAni: Int,
            @AnimRes exitAni: Int
        ): Builder {
            this.enterAni = enterAni
            this.exitAni = exitAni
            return this
        }

        /**
         * Budndle Set Data
         * [Array 는 StringArray 로 사용해주세요.]
         */
        @Suppress("unchecked")
        inline fun <reified T> setBundle(key: String, value: T?): Builder {
            try {
                when (value) {
                    is String -> data.putString(key, value)
                    is Int -> data.putInt(key, value)
                    is Long -> data.putLong(key, value)
                    is Double -> data.putDouble(key, value)
                    is Boolean -> data.putBoolean(key, value)
                    is Float -> data.putFloat(key, value)
                    is Parcelable -> data.putParcelable(key, value)
                    is Array<*> -> data.putStringArray(key, value as? Array<out String>)
                    is Serializable -> data.putSerializable(key, value)
                }
            } catch (ex: Exception) {
                // ignore
            }
            return this
        }

        /**
         * Build Function
         */
        fun build(): ActivityResult {
            return ActivityResult(
                requestCode = requestCode,
                targetActivity = targetActivity,
                flags = flags,
                data = data,
                enterAni = enterAni,
                exitAni = exitAni
            )
        }
    }
}