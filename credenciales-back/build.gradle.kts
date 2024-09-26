plugins {
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
	id("org.openapi.generator") version "7.8.0"
	kotlin("plugin.jpa") version "1.9.24"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	kotlin("kapt") version "1.8.22"
}

group = "com.verificator"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}
val mapStructVersion = "1.5.5.Final"

dependencies {
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	compileOnly("org.projectlombok:lombok")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("jakarta.validation:jakarta.validation-api:2.0.2")
    implementation("javax.servlet:javax.servlet-api:3.1.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.14")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.1.0")
	implementation("org.apache.xmlgraphics:batik-transcoder:1.14")
	implementation("org.apache.xmlgraphics:batik-codec:1.14")
//implementation 'com.google.cloud:google-cloud-vision'

	implementation("org.mapstruct:mapstruct:$mapStructVersion")
	kapt("org.mapstruct:mapstruct-processor:$mapStructVersion")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("org.openscience.cdk:cdk-bundle:2.9")

}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
openApiGenerate {
	generatorName = "kotlin-spring"
	inputSpec = "$rootDir/../openapi.yaml"
	outputDir = "$rootDir"
	groupId = "$group"
	modelNameSuffix = "Dto"

	packageName 	= "documents.api.generated"
	apiPackage 		= "documents.api.generated.api"
	modelPackage 	= "documents.api.generated.dto"

	additionalProperties.put("interfaceOnly", true)
	additionalProperties.put("skipDefaultInterface", true)
	additionalProperties.put("configPackage", "documents.api.generated.config")
	additionalProperties.put("useTags", true)
	additionalProperties.put("hideGenerationTimestamp", true)
}
