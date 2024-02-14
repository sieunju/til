# 소스 파일, 라인정보 유지
-keepattributes LineNumberTable,SourceFile
-renamesourcefileattribute SourceFile

# Remove Log Disable
#-assumenosideeffects class android.util.Log {
#    public static int d(...);
#    public static int e(...);
#}

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