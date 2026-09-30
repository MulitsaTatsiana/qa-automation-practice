plugins {
    kotlin("jvm")
}
dependencies {
    implementation("org.junit.jupiter:junit-jupiter:${property("junitJupiterVersion")}")
    implementation("org.junit.jupiter:junit-jupiter-params:${property("junitJupiterVersion")}")
    implementation("org.assertj:assertj-core:${property("assertJVersion")}")
    implementation("io.rest-assured:rest-assured:${property("restAssuredVersion")}")
    implementation("io.qameta.allure:allure-junit5:${property("allureVersion")}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${property("jacksonVersion")}")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.codeborne:selenide:7.6.1")
    implementation("io.github.bonigarcia:webdrivermanager:5.9.2")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
    }
}
repositories {
    mavenCentral()
}
kotlin {
    jvmToolchain(17)
}