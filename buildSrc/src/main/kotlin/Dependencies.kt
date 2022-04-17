object Repositories {
    const val Paper = "https://papermc.io/repo/repository/maven-public/"
    const val Bungee = "https://oss.sonatype.org/content/repositories/snapshots/"
}

object Dependencies {
    object Paper {
        const val API = "io.papermc.paper:paper-api:1.17-R0.1-SNAPSHOT"

        const val USERDEV = "io.papermc.paperweight.userdev"
        const val USERDEV_VERSION = "1.3.5-SNAPSHOT"
    }
    object Bungee {
        const val API = "net.md-5:bungeecord-api:1.18-R0.1-SNAPSHOT"
    }
}