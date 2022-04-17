import org.gradle.api.Project

private fun Project.subproject(name: String) = project(":${rootProject.name}-$name")

val Project.api; get() = subproject("api")
val Project.core; get() = subproject("core")
val Project.dongle; get() = subproject("dongle")
val Project.pub; get() = subproject("publish")