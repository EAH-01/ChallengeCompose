pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "ChallengeCompose"
include(":app")
include(":feature:home:presentation")
include(":feature:detail:presentation")
include(":core:navigation")
include(":core:network")
include(":core:ui_components")
include(":core:designsystem")
include(":lab")
include(":feature:home:data")
include(":feature:home:domain")
