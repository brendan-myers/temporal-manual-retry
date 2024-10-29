plugins {
	java
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.springdoc.openapi-gradle-plugin") version "1.8.0"
	id("org.openapi.generator") version "5.1.0"
}

group = "com.brendan"
version = "0.0.1-SNAPSHOT"
var temporalVersion = "1.22.3"
var javaSDKVersion = "1.22.3"
var otelVersion = "1.30.1"
var otelVersionAlpha = "${otelVersion}-alpha"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("io.temporal:temporal-spring-boot-starter-alpha:${temporalVersion}")
	testImplementation("org.springframework.boot:spring-boot-starter-test")


    implementation(platform("io.opentelemetry:opentelemetry-bom:${otelVersion}"))
    implementation("io.opentelemetry:opentelemetry-sdk")
    implementation("io.opentelemetry:opentelemetry-exporter-jaeger")
    implementation("io.opentelemetry:opentelemetry-extension-trace-propagators")
    implementation("io.opentelemetry:opentelemetry-opentracing-shim:${otelVersion}")
    implementation("io.opentelemetry:opentelemetry-semconv:${otelVersionAlpha}")
    implementation("io.jaegertracing:jaeger-client:1.8.1")

	implementation("io.temporal:temporal-sdk:${javaSDKVersion}")
    implementation("io.temporal:temporal-opentracing:${javaSDKVersion}")
    testImplementation("io.temporal:temporal-testing:${javaSDKVersion}")

	testImplementation("org.junit.jupiter:junit-jupiter:5.7.0") // Adjust version as necessary

	implementation("jakarta.validation:jakarta.validation-api:3.1.0-M2")
	implementation("org.projectlombok:lombok:1.18.32")

	implementation("org.springdoc:springdoc-openapi-ui:1.8.0")
}

// configure<GenerateTask> {
//     generatorName.set("spring")
//     inputSpec.set("${project.rootDir}/src/main/resources/openapi.yaml")
//     outputDir.set("${project.buildDir}/generated")
//     apiPackage.set("com.brendan.temporal.api")
//     modelPackage.set("com.brendan.temporal.model")
//     configOptions.set(mapOf(
//         "library" to "spring-boot",
//         "dateLibrary" to "java8"
//     ))
// }
// tasks.withType<Test> {
// 	useJUnitPlatform()
// }
