import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import java.io.ByteArrayOutputStream

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.8.10"
    id("io.ktor.plugin") version "2.2.3"
    id("com.github.johnrengelman.shadow") version "7.1.2"
//    id("java")
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-host-common-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-cors-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-jackson-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-websockets-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.14.2")
    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    implementation("com.github.salomonbrys.kodein:kodein:4.1.0")
    implementation("com.moandjiezana.toml:toml4j:0.7.2")
    implementation("com.graphql-java:graphql-java:20.0")
}

//generateVersion {
//    def versionFile = new File("${project.rootDir}/resources/version.txt")
//    versionFile.write ("${version}")
//
//    def gitHashFile = new File("${project.rootDir}/resources/gitHash.txt")
//    def command = "git log --pretty=format:%H -n 1"
//    def gitHash = command.execute().text.trim()
//    gitHashFile.write (gitHash)
//}
//
//build.dependsOn generateVersion

fun String.runCommand(currentWorkingDir: File = file("./")): String {
    val byteOut = ByteArrayOutputStream()
    project.exec {
        workingDir = currentWorkingDir
        commandLine = this@runCommand.split("\\s".toRegex())
        standardOutput = byteOut
    }
    return String(byteOut.toByteArray()).trim()
}

//open class MetadataTask : DefaultTask () {
//    @Input
//    var localVersion: String = ""
//
//    @TaskAction
//    fun invoke () {
//        generateVersion ()
//        generateGitHash()
//        return
//    }
//
//    private fun generateVersion () {
//        val versionFile = File("${project.rootDir}/resources/version.txt")
//        versionFile.writeText (localVersion)
//        return
//    }
//
//    private fun generateGitHash (): String {
//        val gitHashFile = File("${project.rootDir}/resources/gitHash.txt")
//        val command = "git log --pretty=format:%H -n 1"
//        val gitHash = command.runCommand ()
//        gitHashFile.writeText (gitHash)
//        return gitHash
//    }
//}


tasks {
//    register<MetadataTask> ("metadata") {
//        localVersion = "$version"
//    }

    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("sec")
        archiveVersion.set ("1.1.1")
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "com.example.ApplicationKt"))
        }
    }
}

tasks {
    build {
        dependsOn (shadowJar)
    }
}

// EOF