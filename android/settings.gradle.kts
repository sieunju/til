rootProject.name = "til"

include(
    ":app",
    ":data",
    ":model",
    ":domain",
    ":presentation",
    ":loginmanager",
    ":likemanager",
    ":rxbus",
    ":lifecycle",
    ":rxhandling"
)
include(
    ":features:core"
)
include(":features:network")
include(":features:network-requirements")
include(":features:core-ui")
include(":features:recyclerview")
include(":features:recyclerview-requirements")
include(":features:main")
