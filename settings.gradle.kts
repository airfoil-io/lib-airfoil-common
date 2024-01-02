rootProject.name = "lib-airfoil-common"

pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()
        maven("https://plugins.gradle.org/m2/")
	}
}

include(
	"lib"
)
