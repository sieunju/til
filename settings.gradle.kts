rootProject.name = "til"

include(
    ":app",
    ":core"
)
include(":rxhandling")
include(":features:network")
include(":features:network-bridge")
include(":features:recyclerview")
include(":features:recyclerview-bridge")
include(":features:main")
include(":features:base-mvvm")
include(":features:base-mvvm-bridge")
include(":test")
include(":async_migrate")
include(":features:async_migrate")
include(":features:async_migrate_bridge")
include(":features:compose-ui")
include(":features:compose-ui-bridge")
