plugins {
    java
    id("xyz.jpenilla.run-paper") version "3.1.0"
}

group = "xyz.srnyx"
version = "1.0.1"
description = "Hide players vanished with SuperVanish on Pl3xMap"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://api.modrinth.com/maven")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("maven.modrinth:pl3xmap:1.19.4-SNAPSHOT")
    compileOnly("com.github.LeonMangler:SuperVanish:6.2.18-3")
    compileOnly("org.jetbrains:annotations:26.0.2")
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(17)
    }

    processResources {
        filteringCharset = "UTF-8"
    }

    jar {
        archiveClassifier.set("")
    }
}
