plugins {
	java
	id("org.springframework.boot") version "3.5.8"
	id("io.spring.dependency-management") version "1.1.7"
	jacoco
}

group = "br.com.rjs"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

// Sobrescreve as versões gerenciadas pelo Spring BOM
extra["rest-assured.version"] = "6.0.0"

configurations.all {
	resolutionStrategy {
		force("io.rest-assured:rest-assured:6.0.0")
		force("io.rest-assured:json-path:6.0.0")
		force("io.rest-assured:xml-path:6.0.0")
		force("io.rest-assured:rest-assured-common:6.0.0")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.14")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.testcontainers:testcontainers:1.19.8")
	testImplementation("org.testcontainers:mysql:1.19.8")
	testImplementation("org.testcontainers:junit-jupiter:1.19.8")

	// Rest Assured
	testImplementation("io.rest-assured:rest-assured:6.0.0")
	testImplementation("io.rest-assured:json-path:6.0.0")
	testImplementation("io.rest-assured:xml-path:6.0.0")
	testImplementation("io.rest-assured:rest-assured-common:6.0.0")

	// Flyway
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-mysql")

	// XML support
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")

	// YAML support
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")

	// Driver MySQL
	runtimeOnly("com.mysql:mysql-connector-j")
}

tasks.withType<Test> {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport)
}

jacoco {
	toolVersion = "0.8.11"
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		xml.required = true
		html.required = true
	}
}