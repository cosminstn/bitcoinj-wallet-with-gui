import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "tech.bceng"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("no.tornado:tornadofx:1.7.20")
    implementation("com.google.guava:guava:30.1.1-jre")
    implementation("org.bitcoinj:bitcoinj-core:0.15.10")

    implementation("de.jensd:fontawesomefx:8.0.0")
    implementation("com.google.zxing:core:3.4.0")
    implementation("org.slf4j:slf4j-jdk14:1.7.30")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}