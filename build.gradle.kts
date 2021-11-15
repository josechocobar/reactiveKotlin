import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
}

group = "com.chocobar"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("com.h2database:h2")
	runtimeOnly("io.r2dbc:r2dbc-h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")

	// Retrofit
	implementation ("com.squareup.retrofit2:retrofit:2.9.0")
	implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
	implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")

	//coroutines
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
	//koin
	implementation ("io.insert-koin:koin-core:3.0.1")
	testImplementation ("io.insert-koin:koin-test:3.0.1")

	//openapi
	implementation("org.springdoc:springdoc-openapi-webflux-ui:1.4.8")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.4.8")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

