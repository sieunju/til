import com.hmju.til.configComposeAndroid
import com.hmju.til.configHiltAndroid
import com.hmju.til.configKotlinAndroid

plugins {
    id("com.android.application")
}

configHiltAndroid()
configComposeAndroid()
configKotlinAndroid()
