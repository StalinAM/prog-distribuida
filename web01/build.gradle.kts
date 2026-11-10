plugins {
    id("java")
    id("war")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.apache.commons:commons-collections4:4.6.0")
    // Source: https://mvnrepository.com/artifact/jakarta.ws.rs/jakarta.ws.rs-api
    implementation("jakarta.ws.rs:jakarta.ws.rs-api:4.0.0")
}