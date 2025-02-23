import com.hmju.til.configCoroutineAndroid
import com.hmju.til.configHiltAndroid
import com.hmju.til.configKotlinAndroid
import com.hmju.til.configTestAndroid

plugins {
    id("com.android.library")
}

configKotlinAndroid()
configHiltAndroid()
configCoroutineAndroid()
configTestAndroid()