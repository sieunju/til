import com.hmju.til.configComposeAndroid
import com.hmju.til.configHiltAndroid
import com.hmju.til.configKotlinAndroid

// 여기는 안됨...
plugins {
    id("com.android.application")
}

configHiltAndroid()
configComposeAndroid()
configKotlinAndroid()
