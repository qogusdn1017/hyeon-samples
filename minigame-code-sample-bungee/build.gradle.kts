@Suppress("GradlePackageUpdate")
dependencies {
    api(core)
}

// pascal case hello-world -> HelloWorld
val pluginName = rootProject.name.split('-').joinToString("") { it.capitalize() }
// remove dash hello-world -> helloworld
val packageName = rootProject.name.replace("-", "")
// for processResources
extra.set("pluginName", pluginName)
extra.set("packageName", packageName)

tasks {
    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
            expand(extra.properties)
        }
    }

    shadowJar {
        archiveBaseName.set(project.name.removeSuffix("-bungee"))
        archiveClassifier.set("")
        archiveVersion.set("")
    }

    create<Copy>("bungeeJar") {
        from(shadowJar)
        val bungeePlugins = File(rootDir, ".bungee/plugins/")
        into(if (File(bungeePlugins, "${project.name}.jar").exists()) File(bungeePlugins, "update") else bungeePlugins)
    }
}
