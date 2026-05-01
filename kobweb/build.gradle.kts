import com.varabyte.kobweb.gradle.library.util.configAsKobwebLibrary

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kobweb.library)
    // alias(libs.plugins.kobwebx.markdown)
}

group = "net.janhoo.kotlin.kobweb"
version = "1.0-SNAPSHOT"

kotlin {
    configAsKobwebLibrary()

    sourceSets {
        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            // implementation(libs.kobwebx.markdown)
        }
    }
}
