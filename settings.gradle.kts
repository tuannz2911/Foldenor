import java.util.Locale

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

if (!file(".git").exists()) {
    val errorText = """
        
        =====================[ ERROR ]=====================
         The Foldenor project directory is not a properly cloned Git repository.
         
         In order to build Foldenor from source you must clone
         the Foldenor repository using Git, not download a code
         zip from GitHub.
         
         See https://github.com/PaperMC/Paper/blob/master/CONTRIBUTING.md
         for further information on building and modifying Paper and Forks.
        ===================================================
    """.trimIndent()
    error(errorText)
}

rootProject.name = "foldenor"

for (name in listOf("Foldenor-API", "Foldenor-Server")) {
    val projName = name.lowercase(Locale.ENGLISH)
    include(projName)
    findProject(":$projName")!!.projectDir = file(name)
}
