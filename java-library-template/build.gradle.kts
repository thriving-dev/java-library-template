group = "dev.thriving.oss"

object Meta {
    const val release = "https://s01.oss.sonatype.org/service/local/"
    const val snapshot = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
    const val desc = "OSS GitHub Java Library Template Repository"
    const val license = "Apache-2.0"
    const val licenseUrl = "https://opensource.org/licenses/Apache-2.0"
    const val githubRepo = "thriving-dev/java-library-template"
    const val developerId = "hartmut-co-uk"
    const val developerName = "Hartmut Armbruster"
    const val developerOrganization = "thriving.dev"
    const val developerOrganizationUrl = "https://thriving.dev"
}

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
}

sourceSets {
    create("intTest") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

val intTestImplementation by configurations.getting {
    extendsFrom(configurations.implementation.get())
}

configurations["intTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

dependencies {
    // TODO: remove again after verifying 'trivy scan' & 'renovate' integration works
    implementation(libs.snakeyaml)

    testImplementation(libs.junit)

    intTestImplementation(libs.junit)
    intTestImplementation(libs.bundles.testcontainers.junit)
    intTestImplementation(libs.assertj)
}

val intTest = task<Test>("intTest") {
    description = "Runs integration tests."
    group = "verification"

    testClassesDirs = sourceSets["intTest"].output.classesDirs
    classpath = sourceSets["intTest"].runtimeClasspath
    shouldRunAfter("test")

    useJUnitPlatform()

    testLogging {
        events("passed")
    }
}

tasks.check { dependsOn(intTest) }

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

signing {
    val signingKey = providers.environmentVariable("GPG_SIGNING_KEY")
    val signingPassphrase = providers.environmentVariable("GPG_SIGNING_PASSPHRASE")
    if (signingKey.isPresent && signingPassphrase.isPresent) {
        useInMemoryPgpKeys(signingKey.get(), signingPassphrase.get())
        val extension = extensions.getByName("publishing") as PublishingExtension
        sign(extension.publications)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            from(components["java"])
            pom {
                name.set(project.name)
                description.set(Meta.desc)
                url.set("https://github.com/${Meta.githubRepo}")
                licenses {
                    license {
                        name.set(Meta.license)
                        url.set(Meta.licenseUrl)
                    }
                }
                developers {
                    developer {
                        id.set(Meta.developerId)
                        name.set(Meta.developerName)
                        organization.set(Meta.developerOrganization)
                        organizationUrl.set(Meta.developerOrganizationUrl)
                    }
                }
                scm {
                    url.set("https://github.com/${Meta.githubRepo}.git")
                    connection.set("scm:git:git://github.com/${Meta.githubRepo}.git")
                    developerConnection.set("scm:git:git://github.com/${Meta.githubRepo}.git")
                }
                issueManagement {
                    url.set("https://github.com/${Meta.githubRepo}/issues")
                }
            }
        }
    }
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version,
            ),
        )
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

// gradle locking of dependency versions
//   *required+used for trivy scan
dependencyLocking {
    lockAllConfigurations()
}
// always run subproject task with parent
rootProject.tasks.dependencies { dependsOn(tasks.dependencies) }
