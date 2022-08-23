rootProject.name = "til"

include(
    ":app",
    ":data",
    ":model",
    ":domain",
    ":loginmanager",
    ":likemanager",
    ":rxbus",
    ":lifecycle",
    ":rxhandling"
)
include(":features:core")
include(":features:core-ui")
include(":features:network")
include(":features:network-requirements")
include(":features:recyclerview")
include(":features:recyclerview-requirements")
include(":features:main")
include(":features:base-mvvm")
include(":features:base-mvvm-requirements")
include(":test")
