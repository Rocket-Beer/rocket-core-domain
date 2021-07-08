import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

plugins {
    id("maven-publish")
}

publishing {
    repositories {
        maven {
            name = getPublishProperty("github.name")
            url = uri(getPublishProperty("github.url"))
            credentials {
                username = gradleLocalProperties(rootDir).getProperty("github.username")
                password = gradleLocalProperties(rootDir).getProperty("github.password")
            }
        }
    }

    publications {
        create<MavenPublication>("gpr") {
            run {
                groupId = getPublishProperty("groupId")
                artifactId = getPublishProperty("artifactId")
                version = getPublishProperty("version")
                artifact("$buildDir/libs/$artifactId.jar")
            }
        }
    }
}

fun gradlePublisherProperties(projectRootDir: File): Properties {
    val properties = Properties()
    val publisherProperties = File(projectRootDir, "publisher.properties")

    if (publisherProperties.isFile) {
        InputStreamReader(FileInputStream(publisherProperties), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    }
    return properties
}

fun getPublishProperty(property: String): String {
    return gradlePublisherProperties(projectDir).getProperty(property) ?: ""
}

fun getPublisherUsername(): String {
    return gradleLocalProperties(rootDir).getProperty("github.username")
        ?: System.getenv("GITHUB_ACTOR")
}

fun getPublisherPassword(): String {
    return gradleLocalProperties(rootDir).getProperty("github.password")
        ?: System.getenv("GITHUB_TOKEN")
}
