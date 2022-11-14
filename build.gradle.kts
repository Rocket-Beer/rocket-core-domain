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

println("\n********** Sonar configuration for all projects **********")
apply(plugin = "sonar.plugin")

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}