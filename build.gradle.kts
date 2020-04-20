import org.gradle.api.tasks.bundling.Jar

plugins {
    kotlin("jvm") version "1.3.72"
    `maven-publish`
}

group = "com.github.halgorithm"
version = "0.1.8-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

publishing {
    repositories {
        maven {
            name = "lmr-rcd-repo"
            url = uri("https://maven.pkg.github.com/halgorithm/lmr-rcd")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }

    publications {
        create<MavenPublication>("lmr-rcd") {
            from(components["java"])
        }
    }
}
