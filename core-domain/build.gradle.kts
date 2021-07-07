import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

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
            name = "rocket-core-domain"
            url = uri("https://maven.pkg.github.com/Rocket-Beer/rocket-core-domain")
            credentials {
                username = gradleLocalProperties(rootDir).getProperty("github.username")
                password = gradleLocalProperties(rootDir).getProperty("github.password")
            }
        }
    }

    publications {
        create<MavenPublication>("gpr") {
            run {
                groupId = "com.rocket.core"
                artifactId = "core-domain"
                version = "0.0.1"
                artifact("$buildDir/libs/$artifactId.jar")
            }
        }
    }
}
