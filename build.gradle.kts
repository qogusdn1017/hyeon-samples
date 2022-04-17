plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.dokka") version "1.6.10" apply false
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        maven(Repositories.Paper)
        maven(Repositories.Bungee)
    }

    @Suppress("GradlePackageUpdate")
    dependencies {
        compileOnly(Dependencies.Paper.API)
        compileOnly(Dependencies.Bungee.API)
//        compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:${project.properties["coroutinesVersion"]}")

        implementation(kotlin("stdlib"))
        implementation(kotlin("reflect"))
    }
}

fun Project.preparePublish() {
    apply(plugin = "org.jetbrains.dokka")

    tasks {
        create<Jar>("sourcesJar") {
            archiveClassifier.set("sources")
            from(sourceSets["main"].allSource)
        }

        create<Jar>("dokkaJar") {
            archiveClassifier.set("javadoc")
            dependsOn("dokkaHtml")

            from("$buildDir/dokka/html/") {
                include("**")
            }
        }
    }
}

api.preparePublish()
core.preparePublish()

tasks {
    register<DefaultTask>("setupModules") {
        doLast {
            val defaultPrefix = "sample"
            val projectPrefix = rootProject.name

            if (defaultPrefix != projectPrefix) {
                fun rename(suffix: String) {
                    val from = "$defaultPrefix-$suffix"
                    val to = "$projectPrefix-$suffix"
                    file(from).takeIf { it.exists() }?.renameTo(file(to))
                }

                rename("api")
                rename("core")
                rename("dongle")
                rename("paper")
                rename("bungee")
                rename("publish")
            }
        }
    }
}
