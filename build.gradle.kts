import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
//    maven("https://maven.enginehub.org/repo/")
}

@Suppress("GradlePackageUpdate")
dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("io.papermc.paper:paper-api:${project.properties["mcVersion"]}-R0.1-SNAPSHOT")
    compileOnly("io.github.monun:tap-api:${project.properties["tapVersion"]}")
    compileOnly("io.github.monun:kommand-api:${project.properties["kommandVersion"]}")
//    compileOnly("io.github.monun:invfx-api:${project.properties["invfxVersion"]}")
//    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:${project.properties["coroutinesVersion"]}")
//    compileOnly("io.github.monun:heartbeat-coroutines:${project.properties["hbCoroutinesVersion"]}")
//    compileOnly("world.komq:parallel-universe-api:${project.properties["parallelUniverseVersion"]}")
//    compileOnly("com.sk89q.worldedit:worldedit-bukkit:${"worldeditVersion"}")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    }
    processResources {
        filesMatching("**/*.yml") {
            expand(project.properties)
        }
        filteringCharset = "UTF-8"
    }
    register<Jar>("outputJar") {
        archiveBaseName.set(project.name)
        archiveClassifier.set("")
        archiveVersion.set("")

        from(sourceSets["main"].output)

        doLast {
            copy {
                from(archiveFile)
                into("./out")
            }
        }
    }
    register<Jar>("paperJar") {
        archiveBaseName.set(project.name)
        archiveClassifier.set("")
        archiveVersion.set("")

        from(sourceSets["main"].output)

        doLast {
            copy {
                from(archiveFile)
                val plugins = File(rootDir, ".server/plugins/")
                into(if (File(plugins, archiveFileName.get()).exists()) File(plugins, "update") else plugins)
            }
        }
    }
}