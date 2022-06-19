@Suppress("GradlePackageUpdate")
dependencies {
    api(core)
    compileOnly("io.github.monun:tap-api:${project.properties["tapVersion"]}")
    compileOnly("io.github.monun:kommand-api:${project.properties["kommandVersion"]}")
    //    compileOnly("io.github.monun:invfx-api:${project.properties["invfxVersion"]}")
    //    compileOnly("io.github.monun:heartbeat-coroutines:${project.properties["hbCoroutinesVersion"]}")
    //    compileOnly("com.sk89q.worldedit:worldedit-bukkit:${"worldeditVersion"}")
}

tasks {
    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
            expand(extra.properties)
        }
    }

    fun registerPluginJar(name: String, vararg outputs: Project?, configuration: Jar.() -> Unit) = register<Jar>(name) {
        archiveBaseName.set(rootProject.name)
        archiveVersion.set("")

        outputs.filterNotNull().forEach { project ->
            from(project.sourceSets["main"].output)
        }

        configuration()

        doLast {
            copy {
                from(archiveFile)
                val plugins = File(rootDir, ".server/plugins/")
                into(if (File(plugins, archiveFileName.get()).exists()) File(plugins, "update") else plugins)
            }
        }
    }
    
    registerPluginJar("pluginJar", api, core, project) {
        val dongleJar = dongle.tasks.jar

        dependsOn(dongleJar)
        from(zipTree(dongleJar.get().archiveFile))

        exclude("test.yml")
    }
    
    registerPluginJar("testPluginJar", project) {
        exclude("plugin.yml")
        rename("test-plugin.yml", "plugin.yml")

        pub.tasks.let { tasks ->
            dependsOn(tasks.named("publishApiPublicationToDebugRepository"))
            dependsOn(tasks.named("publishCorePublicationToDebugRepository"))
        }
    }
}