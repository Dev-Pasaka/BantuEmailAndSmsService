import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project


plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.0.0"
    kotlin("jvm") version "1.8.0"
    id("io.ktor.plugin") version "2.2.2"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"

}
application {
    mainClass.set("online.pasaka.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    google()
    maven {
       url =  uri("https://jitpack.io")
    }
}


group = "online.pasaka"
version = "0.0.1"

dependencies {

    implementation("com.android.tools:desugar_jdk_libs:1.1.5")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("org.ktorm:ktorm-core:3.5.0")
    implementation("com.mysql:mysql-connector-j:8.0.31")
    implementation("org.mindrot:jbcrypt:0.4")
    //implementation group: "com.twilio.sdk", name: "twilio", version: "9.2.2"
    implementation("com.twilio.sdk:twilio:9.2.2")
    //implementation("com.github.AfricasTalkingLtd:africastalking:1.8.0")

}
tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("shadow")
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "com.github.csolem.gradle.shadow.online.pasaka.ApplicationKt"))
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}



