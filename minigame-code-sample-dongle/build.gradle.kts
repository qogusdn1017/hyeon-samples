plugins {
    id(Dependencies.Paper.USERDEV) version Dependencies.Paper.USERDEV_VERSION apply false
}

val dongleJar = tasks.jar.get()

subprojects {
    apply(plugin = Dependencies.Paper.USERDEV)

    dependencies {
        api(core)
        paperDevBundle("${name.substring(1)}-R0.1-SNAPSHOT")
    }

    val reobfJar = tasks.named("reobfJar").get() as io.papermc.paperweight.tasks.RemapJar

    dongleJar.apply {
        dependsOn(reobfJar)
        from(zipTree(reobfJar.outputJar))
    }
}
