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
include(":features:network-bridge")
include(":features:recyclerview")
include(":features:recyclerview-bridge")
include(":features:main")
include(":features:base-mvvm")
include(":features:base-mvvm-bridge")
include(":test")
include(":features:async_migrate")
include(":features:async_migrate_bridge")
include(":features:network_v2")
include(":features:network_v2-bridge")
include(":features:compose-ui")
include(":features:compose-ui-bridge")
include(":legacy")
include(":features:rv_custom_paging")
include(":features:rv_custom_paging_bridge")
include(":features:network_error_handling")
include(":features:network_error_handling_bridge")
include(":features:network_jsend_format")
include(":features:network_jsend_format_bridge")
include(":features:network_expired_token")
include(":features:network_expired_token_bridge")
include(":features:rv_simple_like")
include(":features:rv_simple_like_bridge")
include(":features:rv_diff_util_performance")
include(":features:rv_diff_util_performance_bridge")
include(":features:rv_refactor_diff_util")
include(":features:rv_refactor_diff_util_bridge")
include(":features:rv_diff_util_2")
include(":features:rv_diff_util_2_bridge")
include(":features:base_mvvm_lifecycle")
include(":features:base_mvvm_lifecycle_bridge")
include(":features:base_mvvm_bottom_sheet")
include(":features:base_mvvm_bottom_sheet_bridge")
include(":features:compose_permissions_result")
include(":features:compose_permissions_result_bridge")
include(":features:compose_navigation")
include(":features:compose_navigation_bridge")
include(":features:room-observer")
include(":features:room-observer-bridge")
