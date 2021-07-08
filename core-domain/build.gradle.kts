import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

plugins {
    id("kotlin")
    id("java-library")
    id("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.20")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0-native-mt")

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.3")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.20")
    testImplementation("io.mockk:mockk:1.12.0")
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
