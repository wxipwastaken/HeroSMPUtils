plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version("7.1.2")
}

group = "network.geode"
version = "1.3"

val relocateLoc = "network.geode.libs"

val paperApiVer = "1.19.4-R0.1-SNAPSHOT"
val paperLibVer = "1.0.8"
val eGlowApiVer = "master-SNAPSHOT"
val lampVer = "3.1.2"
val triumphConfigVer = "1.0.5-SNAPSHOT"
val miniMessageVer = "4.12.0"
val napVer = "master-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/") // PaperMC repo
    maven("https://jitpack.io") // JitPack repo
    maven("https://repo.triumphteam.dev/snapshots/") // TriumphTeam repo
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:$paperApiVer")
    implementation("io.papermc:paperlib:$paperLibVer")
    compileOnly("com.github.mrgraycat:eGlow:$eGlowApiVer")
    implementation("me.mattstudios:triumph-config:$triumphConfigVer")
    implementation("com.github.Revxrsal.Lamp:common:$lampVer")
    implementation("com.github.Revxrsal.Lamp:bukkit:$lampVer")
    implementation("net.kyori:adventure-text-minimessage:$miniMessageVer")
}

tasks {
    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    }
    shadowJar {
        relocate("io.papermc.lib", "$relocateLoc.paperlib")
        relocate("me.mattstudios.config", "$relocateLoc.config")
        relocate("revxrsal.commands", "$relocateLoc.commands")
    }
    build {
        dependsOn(shadowJar)
    }
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    jar {
        archiveFileName.set("${project.name}-${project.version}.jar")
    }
}