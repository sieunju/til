rootProject.name = "til"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

include(":app")
include(":core")
include(":rxhandling")
include(":features:network")
include(":features:recyclerview")
include(":features:main")
include(":features:base-mvvm")
include(":test")
include(":features:async_migrate")
include(":features:network_v2")
include(":features:compose-ui")
include(":legacy")
include(":features:rv_custom_paging")
include(":features:network_error_handling")
include(":features:network_jsend_format")
include(":features:network_expired_token")
include(":features:rv_simple_like")
include(":features:rv_diff_util_performance")
include(":features:rv_refactor_diff_util")
include(":features:rv_diff_util_2")
include(":features:base_mvvm_lifecycle")
include(":features:base_mvvm_bottom_sheet")
include(":features:compose_permissions_result")
include(":features:compose_navigation")
include(":features:room-observer")
include(":core-navigator")
