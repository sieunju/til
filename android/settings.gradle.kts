rootProject.name = "til"

include(
    ":app",
    ":core",
    ":shared"
)
include(":rxhandling")
include(":features:core-ui")
include(":features:network")
include(":features:network-requirements")
include(":features:recyclerview")
include(":features:recyclerview-requirements")
include(":features:main")
include(":features:base-mvvm")
include(":features:base-mvvm-requirements")
include(":test")
include(":network")
