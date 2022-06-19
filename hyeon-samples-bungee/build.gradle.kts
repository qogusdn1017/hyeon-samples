@Suppress("GradlePackageUpdate")
dependencies {
    api(core)
}

tasks {
    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
            expand(extra.properties)
        }
    }

    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")
        archiveVersion.set("")
    }

    create<Copy>("bungeeJar") {
        from(shadowJar)
        val bungeePlugins = File(rootDir, ".bungee/plugins/")
        into(if (File(bungeePlugins, "${rootProject.name}.jar").exists()) File(bungeePlugins, "update") else bungeePlugins)
    }
}