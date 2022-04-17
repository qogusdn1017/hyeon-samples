import java.nio.charset.StandardCharsets

plugins {
    `maven-publish`
    signing
}

val githubUser = "qogusdn1017"
val kwDirFile = File(System.getProperty("user.home").toString())

val komworldDir = if (!kwDirFile.exists()) {
    kwDirFile.mkdirs()
    kwDirFile
}
else kwDirFile

val credentialFile = File(komworldDir, "NexusCredentials.txt")

publishing {
    repositories {
        mavenLocal()

        maven {
            name = "debug"
            url = rootProject.uri(".server/libraries")
        }

        maven {
            name = "central"

            credentials.runCatching {
                if (!credentialFile.exists()) credentialFile.createNewFile()

                val nexusUsername = credentialFile.readLines(StandardCharsets.UTF_8)[0]
                val nexusPassword = credentialFile.readLines(StandardCharsets.UTF_8)[1]

                if (credentialFile.readLines(StandardCharsets.UTF_8).size == 2 && (nexusUsername.isNotBlank() || nexusPassword.isNotBlank())) {
                    logger.info("Current Nexus Username: ${credentialFile.readLines(StandardCharsets.UTF_8)[0]}")
                    logger.info("Current Nexus Password: ${credentialFile.readLines(StandardCharsets.UTF_8)[1]}")

                    username = nexusUsername
                    password = nexusPassword
                }
            }.onFailure {
                logger.warn("Failed to load nexus credentials, check if credential file contains valid credentials.")
                logger.warn("Other lines will be ignored.")
            }

            url = uri(
                if ("SNAPSHOT" in version as String) {
                    "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                } else {
                    "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
                }
            )

            logger.info("Publishing URL: $url")
        }
    }

    publications {
        fun MavenPublication.setup(target: Project) {
            from(target.components["java"])
            artifact(target.tasks["sourcesJar"])
            artifact(target.tasks["dokkaJar"])

            pom {
                name.set(target.name)
                url.set("https://github.com/${githubUser}/${rootProject.name}")

                licenses {
                    license {
                        name.set("GNU General Public License version 3")
                        url.set("https://opensource.org/licenses/GPL-3.0")
                    }
                }

                developers {
                    developer {
                        id.set("qogusdn1017")
                        name.set("Bae Hyeon Woo")
                        email.set("qogusdn1017@naver.com")
                        url.set("https://github.com/qogusdn1017")
                        roles.addAll("developer")
                        timezone.set("Asia/Seoul")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/${githubUser}/${rootProject.name}.git")
                    developerConnection.set("scm:git:ssh://github.com:${githubUser}/${rootProject.name}.git")
                    url.set("https://github.com/${githubUser}/${rootProject.name}")
                }
            }
        }

        create<MavenPublication>("api") {
            val api = api
            artifactId = api.name
            setup(api)
        }

        create<MavenPublication>("core") {
            val core = core
            val dongle = dongle

            artifactId = core.name
            setup(core)

            core.tasks.jar { archiveClassifier.set("origin") }
            dongle.tasks {
                create<Jar>("publishJar") {
                    archiveBaseName.set(artifactId)

                    from(core.sourceSets["main"].output)
                    dependsOn(jar)
                    from(zipTree(jar.get().archiveFile))

                    artifact(this)
                }
            }
        }
    }
}

signing {
    isRequired = true
    sign(publishing.publications)
}