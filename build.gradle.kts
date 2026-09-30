plugins {
    id("java")
}

allprojects {
    group = "by.t366.study.api"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:${property("lombokVersion")}")
        annotationProcessor("org.projectlombok:lombok:${property("lombokVersion")}")
        testCompileOnly("org.projectlombok:lombok:${property("lombokVersion")}")
        testAnnotationProcessor("org.projectlombok:lombok:${property("lombokVersion")}")
    }
}
