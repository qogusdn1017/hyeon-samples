import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

val mcVerison = project.properties["mcVersion"].toString().split("-").toMutableList().apply { if (size == 3) removeLast() }.joinToString("-")

@Suppress("GradlePackageUpdate")
dependencies {
    implementation(kotlin("stdlib"))
    implementation("net.md-5:bungeecord-api:${mcVerison}-R0.1-SNAPSHOT")
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

    create<Copy>("outputJar") {
        from(shadowJar)
        into("./out")
    }
    create<Copy>("bungeeJar") {
        from(shadowJar)
        val bungeePlugins = File(rootDir, ".bungee/plugins/")
        into(if (File(bungeePlugins, "${project.name}.jar").exists()) File(bungeePlugins, "update") else bungeePlugins)
    }
}