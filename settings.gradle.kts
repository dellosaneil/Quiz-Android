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

rootProject.name = "Quiz Android"
include(":app")
include(":data")
include(":domain")
include(":feature")
include(":common")
include(":feature:quiz")
include(":feature:dashboard")
include(":feature:admin")
