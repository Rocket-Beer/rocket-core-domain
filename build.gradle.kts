/*buildscript {
    repositories {
        maven { url = "https://plugins.gradle.org/m2/" }
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.1")
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20"
        classpath("org.jlleitschuh.gradle:ktlint-gradle:10.1.0")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.17.1")
    }
}*/

allprojects {
    repositories {
        google()
        mavenCentral()

        maven {
            println("**** Rocket Beer maven ****")

            url = uri("https://maven.pkg.github.com/Rocket-Beer/*")

            credentials {
                val userName = publish.CommonMethods.getPublisherUserName(rootProject)
                val userPass = publish.CommonMethods.getPublisherPassword(rootProject)

                println("user = $userName :: password = $userPass")

                username = userName
                password = userPass
            }
        }
    }
}

subprojects {
    println("\n********** Configuration for == $project == **********")
    apply(plugin = "rocket-plugin")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}