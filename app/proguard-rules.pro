# 소스 파일, 라인정보 유지
-keepattributes LineNumberTable,SourceFile
-renamesourcefileattribute SourceFile

# Remove Log Disable
#-assumenosideeffects class android.util.Log {
#    public static int d(...);
#    public static int e(...);
#}

# [s] 난독화시 missing mappging 에 나온것들
-dontwarn java.lang.invoke.StringConcatFactory

-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE
# [e] 난독화시 missing mappging 에 나온것들

# [s] R8 Gson
-keep class com.google.gson.reflect.TypeToken
-keep class * extends com.google.gson.reflect.TypeToken
-keep public class * implements java.lang.reflect.Type
# [e] R8 Gson

# [s] Rx, Coroutine
-keep,allowobfuscation,allowshrinking class io.reactivex.rxjava3.core.Flowable
-keep,allowobfuscation,allowshrinking class io.reactivex.rxjava3.core.Maybe
-keep,allowobfuscation,allowshrinking class io.reactivex.rxjava3.core.Observable
-keep,allowobfuscation,allowshrinking class io.reactivex.rxjava3.core.Single

-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
# [e] Rx, Coroutine

# [s] Data Model
-keepattributes Annotation, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-dontnote kotlinx.serialization.SerializationKt
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

-keep class com.hmju.core.models.** { *; }
-keepclassmembers class com.hmju.core.models.** { *;}

-keep class com.features.*.models.entity.** { *; }
-keepclassmembers class com.features.*.models.entity.** { *;}

-keep class com.features.*.models.body.** { *; }
-keepclassmembers class com.features.*.models.body.** { *;}

-keep class com.features.*.models.meta.** { *; }
-keepclassmembers class com.features.*.models.meta.** { *;}

# -keep,allowobfuscation,allowshrinking class com.features.*.models.entity.**
# R8 full mode strips signatures from non-kept items.
-keep,allowobfuscation,allowshrinking interface com.hmju.core.models.base.ApiResponse
# [s] Data Model

# [s] Glide
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep class com.bumptech.glide.GeneratedAppGlideModuleImpl
# [e] Glide

# [s] ItemListAdapter 에서 자동으로 ViewHolder Constructor 하기위한 선언문
-keep class * extends com.hmju.core.ui.viewholders.BaseViewHolder { <init>();*;}
# [e] ItemListAdapter 에서 자동으로 ViewHolder Constructor 하기위한 선언문