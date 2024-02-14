# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontwarn java.lang.invoke.StringConcatFactory

# [s] Data Model
#-keepattributes Annotation, InnerClasses
#-dontnote kotlinx.serialization.AnnotationsKt
#-dontnote kotlinx.serialization.SerializationKt
#-keep,includedescriptorclasses class com.features.recyclerview.models.entity.**$$serializer { *; }
#-keepclassmembers class com.features.network.models.entity.** {
#    *** Companion;
#}
#-keepclasseswithmembers class com.features.network.models.entity.** {
#    kotlinx.serialization.KSerializer serializer(...);
#}
#-keepclassmembers class kotlinx.serialization.json.** {
#    *** Companion;
#}
#-keepclasseswithmembers class kotlinx.serialization.json.** {
#    kotlinx.serialization.KSerializer serializer(...);
#}
## Rules needed for kotlinx.serialization
#-if @kotlinx.serialization.Serializable class **
#-keep class <1> {
#    *;
#}
# [e] Data Model

#-keep class com.features.recyclerview.models.entity.** {*;}
#-keep class com.features.recyclerview.models.entity.**$* {*;}

# Keep generic signature of RxJava3 (R8 full mode strips signatures from non-kept items).
#-keep,allowobfuscation,allowshrinking class io.reactivex.rxjava3.core.Flowable
#-keep,allowobfuscation,allowshrinking class io.reactivex.rxjava3.core.Maybe
#-keep,allowobfuscation,allowshrinking class io.reactivex.rxjava3.core.Observable
#-keep,allowobfuscation,allowshrinking class io.reactivex.rxjava3.core.Single