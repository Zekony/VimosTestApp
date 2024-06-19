pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "VimosTestApp"
include(":app")
include(":feature:authentication")

include(":data:registration")
include(":domain:registration")
include(":resources")
include(":feature:products")
include(":data:products")
include(":domain:products")
include(":feature:productInfo")
include(":utilities")
